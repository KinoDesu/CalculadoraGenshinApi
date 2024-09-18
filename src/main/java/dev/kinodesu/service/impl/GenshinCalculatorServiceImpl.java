package dev.kinodesu.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.kinodesu.model.request.GenshinApiLanguageEnum;
import dev.kinodesu.model.request.GenshinUser;
import dev.kinodesu.model.request.calculator.*;
import dev.kinodesu.model.response.GenshinCharacter;
import dev.kinodesu.model.response.GenshinSkill;
import dev.kinodesu.model.response.GenshinWeapon;
import dev.kinodesu.model.response.calculator.CalculatorResponse;
import dev.kinodesu.model.response.calculator.material_consume.MaterialConsume;
import dev.kinodesu.model.response.calculator.material_consume.item.CalculatorCharacter;
import dev.kinodesu.service.GenshinCalculatorService;
import dev.kinodesu.service.GenshinCharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenshinCalculatorServiceImpl implements GenshinCalculatorService {
    private final ObjectWriter writer;
    private final GenshinCharacterService genshinCharacterService;

    @Override
    public Optional<CalculatorResponse> calculate(GenshinUser genshinUser, CharacterEvolve characterEvolve,
                                                  GenshinApiLanguageEnum selectedLang) {

        GenshinCharacter genshinCharacter =
                genshinCharacterService.getOwnedCharacterById(characterEvolve.getId(),
                        selectedLang, genshinUser.getUid());

        String requestBody = getCalculatorRequestBody(genshinUser, characterEvolve, genshinCharacter, selectedLang);

        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();

        HttpRequest request =
                HttpRequest.newBuilder().uri(URI.create("https://sg-public-api.hoyolab" + ".com/event" +
                        "/calculateos/batch_compute")).header("Content-Type", "application/json").header("Cookie",
                        String.format("ltmid_v2=%s; ltoken_v2=%s", genshinUser.getMid(), genshinUser.getToken())).POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();

        Optional<CalculatorResponse> calculatorResponseOptional = Optional.empty();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String resposeBody = response.body();

            ObjectMapper mapper = new ObjectMapper();
            CalculatorResponse calculatorResponse = mapper.readValue(resposeBody, CalculatorResponse.class);

            //avatar

            List<MaterialConsume> avatarConsumeList =
                    calculatorResponse.getData().getOverall_material_consume().getAvatar_consume();

            List<CalculatorCharacter> calculatorCharacterList = avatarConsumeList.stream()
                    .flatMap(avatarConsume -> avatarConsume.getAvatars().stream())
                    .toList();

            calculatorCharacterList.stream().filter(x -> x.getId() == genshinCharacter.getId()).forEach(y -> {
                y.setAvatar_target_level(characterEvolve.getCharacterTargetLevel());
                y.setAvatar_current_level(Integer.parseInt(genshinCharacter.getLevel_current()));
            });

            //end avatar


            calculatorResponseOptional = Optional.of(calculatorResponse);
        } catch (IOException | InterruptedException e) {
            log.error(e.toString());
        }

        return calculatorResponseOptional;
    }

    private String getCalculatorRequestBody(GenshinUser genshinUser, CharacterEvolve characterEvolve,
                                            GenshinCharacter genshinCharacter,
                                            GenshinApiLanguageEnum selectedLang) {

        List<CalculatorRequestCharacter> calculatorRequestCharacterList = new ArrayList<>();

        CalculatorRequestCharacter calculatorRequestCharacter = mapCharacter(genshinCharacter,
                characterEvolve.getCharacterTargetLevel(), characterEvolve.getWeaponTargetLevel());

        calculatorRequestCharacterList.add(calculatorRequestCharacter);
        CalculatorRequestBody requestBody = new CalculatorRequestBody();
        requestBody.setItems(calculatorRequestCharacterList);
        requestBody.setLang(selectedLang.getAlias());
        requestBody.setRegion("os_usa");
        requestBody.setUid(genshinUser.getUid());

        String requestBodyJson = "";
        try {
            requestBodyJson = writer.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            log.error(e.toString());
        }
        return requestBodyJson;
    }

    private CalculatorRequestCharacter mapCharacter(GenshinCharacter character, int characterLevelTarget,
                                                    int weaponLevelTarget) {
        CalculatorRequestCharacter calculatorRequestCharacter = new CalculatorRequestCharacter();
        CalculatorRequestWeapon weapon = mapWeapon(character.getWeapon(), weaponLevelTarget);
        List<CalculatorRequestSkill> skill = mapSkill(character.getSkill_list());


        calculatorRequestCharacter.setAvatar_id(character.getId());
        calculatorRequestCharacter.setAvatar_level_current(Integer.parseInt(character.getLevel_current()));
        calculatorRequestCharacter.setWeapon(weapon);
        calculatorRequestCharacter.setSkill_list(skill);
        calculatorRequestCharacter.setElement_attr_id(character.getElement_attr_id());

        boolean checkTargetLevel = checkTargetLevel(Integer.parseInt(character.getLevel_current()),
                character.getMax_level(), characterLevelTarget);


        if (checkTargetLevel) {
            calculatorRequestCharacter.setAvatar_level_target(characterLevelTarget);
        } else {
            calculatorRequestCharacter.setAvatar_level_target(character.getMax_level());
        }

        return calculatorRequestCharacter;
    }

    private CalculatorRequestWeapon mapWeapon(GenshinWeapon weapon, int weaponLevelTarget) {
        CalculatorRequestWeapon calculatorRequestWeapon = new CalculatorRequestWeapon();
        calculatorRequestWeapon.setId(weapon.getId());
        calculatorRequestWeapon.setIcon(weapon.getIcon());
        calculatorRequestWeapon.setWeapon_cat_id(weapon.getWeapon_cat_id());
        calculatorRequestWeapon.setWeapon_level(weapon.getWeapon_level());
        calculatorRequestWeapon.setName(weapon.getName());
        calculatorRequestWeapon.setLevel_current(weapon.getLevel_current());
        calculatorRequestWeapon.setMax_level(weapon.getMax_level());

        boolean checkTargetLevel = checkTargetLevel(weapon.getLevel_current(), weapon.getMax_level(),
                weaponLevelTarget);


        if (checkTargetLevel) {
            calculatorRequestWeapon.setLevel_target(weaponLevelTarget);
        } else {
            calculatorRequestWeapon.setLevel_target(weapon.getMax_level());
        }


        return calculatorRequestWeapon;
    }

    private boolean checkTargetLevel(int currentLevel, int maxLevel, int levelTarget) {

        return !(levelTarget >= maxLevel || levelTarget <= currentLevel || levelTarget <= 0);
    }

    private List<CalculatorRequestSkill> mapSkill(List<GenshinSkill> skillList) {
        List<CalculatorRequestSkill> calculatorRequestSkillList = new ArrayList<>();
        for (GenshinSkill genshinSkill : skillList) {
            CalculatorRequestSkill calculatorRequestSkill = new CalculatorRequestSkill();
            calculatorRequestSkill.setId(genshinSkill.getGroup_id());

            int maxLevel = genshinSkill.getMax_level();

            calculatorRequestSkill.setLevel_current(genshinSkill.getLevel_current());
            if (maxLevel == 1) {
                calculatorRequestSkill.setLevel_current(1);
            }
            calculatorRequestSkill.setLevel_target(genshinSkill.getMax_level());
            calculatorRequestSkillList.add(calculatorRequestSkill);
        }

        return calculatorRequestSkillList;
    }
}
