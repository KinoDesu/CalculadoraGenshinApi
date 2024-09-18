package dev.kinodesu.service;

import dev.kinodesu.model.request.GenshinApiLanguageEnum;
import dev.kinodesu.model.request.GenshinUser;
import dev.kinodesu.model.request.calculator.CharacterEvolve;
import dev.kinodesu.model.response.calculator.CalculatorResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GenshinCalculatorService {
    Optional<CalculatorResponse> calculate(GenshinUser genshinUser, CharacterEvolve character,
                                           GenshinApiLanguageEnum selectedLang);
}
