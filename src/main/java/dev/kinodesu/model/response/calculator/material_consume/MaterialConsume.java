package dev.kinodesu.model.response.calculator.material_consume;

import dev.kinodesu.model.response.calculator.item.ConsumeItem;
import dev.kinodesu.model.response.calculator.material_consume.item.CalculatorCharacter;
import dev.kinodesu.model.response.calculator.material_consume.item.CalculatorConsume;
import dev.kinodesu.model.response.calculator.material_consume.item.CharacterWeapon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MaterialConsume {
    private List<ConsumeItem> consume;
    private List<CalculatorCharacter> avatars;
    private List<CharacterWeapon> weapons;

    public MaterialConsume() {
    }

    public MaterialConsume(List<ConsumeItem> consume, List<CalculatorCharacter> avatars,
                           List<CharacterWeapon> weapons) {
        this.consume = consume;
        this.avatars = avatars;
        this.weapons = weapons;
    }

}
