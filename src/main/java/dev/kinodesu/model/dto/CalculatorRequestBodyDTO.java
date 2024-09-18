package dev.kinodesu.model.dto;

import dev.kinodesu.model.request.calculator.CharacterEvolve;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculatorRequestBodyDTO {
    private String uid;
    private CharacterEvolve character;
}
