package dev.kinodesu.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kinodesu.infra.repository.GenshinCharacterRepository;
import dev.kinodesu.model.request.GenshinApiLanguageEnum;
import dev.kinodesu.model.request.GenshinApiRequestBody;
import dev.kinodesu.model.request.GenshinUser;
import dev.kinodesu.model.response.GenshinApiResponse;
import dev.kinodesu.model.response.GenshinCharacter;
import dev.kinodesu.service.GenshinCharacterService;
import dev.kinodesu.service.GenshinUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenshinCharacterServiceImpl implements GenshinCharacterService {
    private final GenshinUserService genshinUserService;
    private final GenshinCharacterRepository genshinCharacterRepository;

    @Override
    public List<GenshinCharacter> getOwnedCharacterList(String characterName, GenshinApiLanguageEnum selectedLang,
                                                        String uid) {

        GenshinUser genshinUser = genshinUserService.getUserByUid(uid).orElse(null);

        if (genshinUser == null) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400));
        }

        GenshinApiRequestBody requestBody = getRequestBody(selectedLang, genshinUser.getUid(), characterName);

        HttpResponse<String> response = genshinCharacterRepository.findAllOwnedCharacters(requestBody, genshinUser);

        if (response == null) {
            return new ArrayList<>(1);
        }

        updateUser(response, genshinUser);

        String responseBody = response.body();
        GenshinApiResponse genshinApiResponse = mapToGenshinApiResponse(responseBody);
        List<GenshinCharacter> characterList = new ArrayList<>(1);

        if (genshinApiResponse != null) {
            characterList = genshinApiResponse.getData().getList();

            if (characterName.isEmpty())
                genshinCharacterRepository.setOwnedGenshinCharacterList(characterList);
        }
        return characterList;
    }

    @Override
    public GenshinCharacter getOwnedCharacterById(int id, GenshinApiLanguageEnum selectedLang,
                                                  String uid) {

        GenshinUser genshinUser = genshinUserService.getUserByUid(uid).orElse(null);

        if (genshinUser == null) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400));
        }

        List<GenshinCharacter> characterList;
        characterList = getOwnedCharacterList("", selectedLang, genshinUser.getUid());

        return characterList.stream().filter(character -> character.getId() == id).findFirst().orElse(null);
    }

    private GenshinApiRequestBody getRequestBody(GenshinApiLanguageEnum selectedLang, String uid,
                                                 String characterName) {

        return new GenshinApiRequestBody.Builder()
                .uid(uid)
                .region("os_usa")
                .keywords(characterName)
                .element_attr_ids(new ArrayList<>())
                .weapon_cat_ids(new ArrayList<>())
                .page(1)
                .size(2000)
                .lang(selectedLang.getAlias())
                .build();
    }

    private GenshinApiResponse mapToGenshinApiResponse(String responseBody) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseBody, GenshinApiResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String extractCookieValue(String cookieHeader, String cookieName) {
        String cookiePattern = cookieName + "=([^;]+)";
        Pattern pattern = java.util.regex.Pattern.compile(cookiePattern);
        Matcher matcher = pattern.matcher(cookieHeader);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    private void updateUser(HttpResponse<String> response, GenshinUser genshinUser) {
        List<String> headerCookiesList = response.headers().allValues("set-cookie");
        String headerCookies = String.join("", headerCookiesList);

        String newToken = extractCookieValue(headerCookies, "ltoken_v2");
        String newMid = extractCookieValue(headerCookies, "ltmid_v2");

        GenshinUser newGenshinUser = new GenshinUser(genshinUser.getUid(), newToken, newMid, genshinUser.getName());
        genshinUserService.updateUser(newGenshinUser);
    }

}
