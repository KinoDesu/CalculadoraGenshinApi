package dev.kinodesu.model.response.calculator;


import com.fasterxml.jackson.annotation.JsonProperty;
import dev.kinodesu.model.response.calculator.data.AvailableMaterial;
import dev.kinodesu.model.response.calculator.data.CalculatorItem;
import dev.kinodesu.model.response.calculator.data.OverallConsume;
import dev.kinodesu.model.response.calculator.data.OverallMaterialConsume;

import java.util.List;

public class CalculatorResponseData {
    private List<CalculatorItem> items;
    private List<AvailableMaterial> available_material;
    private List<OverallConsume> overall_consume;
    private OverallMaterialConsume overall_material_consume;
    @JsonProperty("HasUserInfo")
    private boolean hasUserInfo;

    public CalculatorResponseData() {
    }

    public CalculatorResponseData(List<CalculatorItem> items, List<AvailableMaterial> available_material, List<OverallConsume> overall_consume, OverallMaterialConsume overall_material_consume, boolean hasUserInfo) {
        this.items = items;
        this.available_material = available_material;
        this.overall_consume = overall_consume;
        this.overall_material_consume = overall_material_consume;
        this.hasUserInfo = hasUserInfo;
    }

    public List<CalculatorItem> getItems() {
        return items;
    }

    public void setItems(List<CalculatorItem> items) {
        this.items = items;
    }

    public List<AvailableMaterial> getAvailable_material() {
        return available_material;
    }

    public void setAvailable_material(List<AvailableMaterial> available_material) {
        this.available_material = available_material;
    }

    public List<OverallConsume> getOverall_consume() {
        return overall_consume;
    }

    public void setOverall_consume(List<OverallConsume> overall_consume) {
        this.overall_consume = overall_consume;
    }

    public OverallMaterialConsume getOverall_material_consume() {
        return overall_material_consume;
    }

    public void setOverall_material_consume(OverallMaterialConsume overall_material_consume) {
        this.overall_material_consume = overall_material_consume;
    }

    public boolean isHasUserInfo() {
        return hasUserInfo;
    }

    public void setHasUserInfo(boolean hasUserInfo) {
        this.hasUserInfo = hasUserInfo;
    }
}
