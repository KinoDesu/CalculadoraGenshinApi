package dev.kinodesu.controller;

import dev.kinodesu.model.request.GenshinApiLanguageEnum;
import dev.kinodesu.model.response.GenshinCharacter;
import dev.kinodesu.service.impl.GenshinCharacterServiceImpl;
import dev.kinodesu.service.impl.GenshinUserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Tag(name = "Genshin Character")
@RestController
@RequestMapping("character")
@RequiredArgsConstructor
public class GenshinCharacterApiController {
    private final GenshinCharacterServiceImpl genshinCharacterServiceImpl;
    private final GenshinUserServiceImpl genshinUserServiceImpl;

    @GetMapping
    public ResponseEntity<Object> getOwnedCharacterList(@RequestParam String uid,
                                                        @RequestParam GenshinApiLanguageEnum lang,
                                                        @RequestParam Optional<String> characterName) {

        try {
            List<GenshinCharacter> genshinCharacterList =
                    genshinCharacterServiceImpl.getOwnedCharacterList(characterName.orElse(""), lang, uid);
            return ResponseEntity.ok().body(genshinCharacterList);

        } catch (HttpClientErrorException exception) {
            return ResponseEntity.status(exception.getStatusCode()).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOwnedCharacterById(@RequestParam String uid,
                                                        @RequestParam GenshinApiLanguageEnum lang,
                                                        @PathVariable int id) {

        GenshinCharacter genshinCharacter = genshinCharacterServiceImpl.getOwnedCharacterById(id, lang, uid);

        if (genshinCharacter == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(genshinCharacter);
    }
}
