package me.smessie.MultiLanguage.api;

import me.smessie.MultiLanguage.main.Languages;

import java.util.HashMap;

public enum Language {

    DUTCH("NL"),
    ENGLISH("EN"),
    FRENCH("FR"),
    GERMAN("DE"),
    SPANISH("ES"),
    RUSSIAN("RU"),
    LATVIAN("LV"),
    DANSK("DK"),
    HUNGARIAN("HU"),
    ITALIAN("IT"),
    BULGARIAN("BG"),
    CHINESE("CHS"),
    POLISH("PL"),
    PORTUGUESE("PT"),
    SLOVENIA("SLU"),
    LITHUANIAN("LT"),
    TURKISH("TR"),
    SLOVAK("SK"),
    CZECH("CS");

    private final String language;

    Language(String language) {
        this.language = language;
    }

    /**
     * Get the Language parsed in String format
     *
     * @return abbreviation of the language
     */
    public String toString() {
        return language;
    }

    private final static HashMap<String, Language> STRING_LANGUAGE_HASH_MAP = new HashMap<String, Language>() {
        {
            put("NL", DUTCH);
            put("EN", ENGLISH);
            put("FR", FRENCH);
            put("DE", GERMAN);
            put("ES", SPANISH);
            put("RU", RUSSIAN);
            put("LV", LATVIAN);
            put("DK", DANSK);
            put("HU", HUNGARIAN);
            put("IT", ITALIAN);
            put("BG", BULGARIAN);
            put("CHS", CHINESE);
            put("PL", POLISH);
            put("PT", PORTUGUESE);
            put("SLU", SLOVENIA);
            put("LT", LITHUANIAN);
            put("TR", TURKISH);
            put("SK", SLOVAK);
            put("CS", CZECH);
        }
    };

    /**
     * Parse a language in String format to the Language object
     *
     * @param language A language in String format
     * @return The Language
     */
    public static Language getLanguageFromString(String language) {
        if (Languages.languagesFull.containsKey(language)) {
            language = Languages.languagesFull.get(language);
        } else if (Languages.languagesOwn.containsKey(language)) {
            language = Languages.languagesOwn.get(language);
        }
        return STRING_LANGUAGE_HASH_MAP.get(language);
    }
}
