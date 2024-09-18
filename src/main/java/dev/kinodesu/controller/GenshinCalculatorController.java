package dev.kinodesu.controller;

import dev.kinodesu.model.dto.CalculatorRequestBodyDTO;
import dev.kinodesu.model.request.GenshinApiLanguageEnum;
import dev.kinodesu.model.request.GenshinUser;
import dev.kinodesu.model.request.calculator.CharacterEvolve;
import dev.kinodesu.model.response.calculator.CalculatorResponse;
import dev.kinodesu.service.GenshinCalculatorService;
import dev.kinodesu.service.GenshinUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Genshin Calculator")
@RestController
@RequestMapping("calculator")
@RequiredArgsConstructor
public class GenshinCalculatorController {

    private final GenshinCalculatorService genshinCalculatorService;
    private final GenshinUserService genshinUserService;


    @PostMapping
    public ResponseEntity<Object> calculateCharacter(@RequestParam GenshinApiLanguageEnum lang,
                                                     @RequestBody CalculatorRequestBodyDTO calculatorRequestBodyDTO) {

        GenshinUser genshinUser = genshinUserService.getUserByUid(calculatorRequestBodyDTO.getUid()).orElse(null);


        if (genshinUser == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        CharacterEvolve character = calculatorRequestBodyDTO.getCharacter();

        CalculatorResponse response =
                genshinCalculatorService.calculate(genshinUser, character, lang).orElse(null);

        if (response == null) return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok().body(response);
    }
}
