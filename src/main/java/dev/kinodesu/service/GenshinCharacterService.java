package dev.kinodesu.service;

import dev.kinodesu.model.request.GenshinApiLanguageEnum;
import dev.kinodesu.model.request.GenshinUser;
import dev.kinodesu.model.response.GenshinCharacter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenshinCharacterService {
    List<GenshinCharacter> getOwnedCharacterList(String characterName, GenshinApiLanguageEnum selectedLang,
                                                 String uid);

    GenshinCharacter getOwnedCharacterById(int id, GenshinApiLanguageEnum selectedLang,
                                           String uid);
}
