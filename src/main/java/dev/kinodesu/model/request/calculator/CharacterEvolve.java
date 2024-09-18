package dev.kinodesu.model.request.calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterEvolve {
    private int id;
    private int characterTargetLevel;
    private int weaponTargetLevel;
}
