package dev.kinodesu.model.request;

import java.util.Arrays;
import java.util.List;

public enum GenshinApiLanguageEnum {
    CHINESE("简体中文", "zh-cn"),
    TAIWANESE("繁體中文", "zh-tw"),
    GERMAN("Deutsch", "de-de"),
    ENGLISH("English", "en-us"),
    SPANISH("Español", "es-es"),
    FRANCE("Français", "fr-fr"),
    INDONESIAN("Indonesia", "id-id"),
    ITALIAN("Italiano", "it-it"),
    JAPANESE("日本語", "ja-jp"),
    KOREAN("한국어", "ko-kr"),
    PORTUGUESE("Português", "pt-pt"),
    RUSSIAN("Pусский", "ru-ru"),
    THAI("ภาษาไทย", "th-th"),
    TURKISH("Türkçe", "tr-tr"),
    VIETNAMESE("Tiếng Việt", "vi-vn");

    private String name;
    private String alias;

    GenshinApiLanguageEnum(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public static GenshinApiLanguageEnum getByAlias(String alias) {
        GenshinApiLanguageEnum result = Arrays.stream(GenshinApiLanguageEnum.values()).filter(lang -> lang.alias.equalsIgnoreCase(alias)).findFirst().orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new RuntimeException();
        }
    }
}
