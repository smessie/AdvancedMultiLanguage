package me.smessie.MultiLanguage.api;

import me.smessie.MultiLanguage.main.Languages;

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
    CHINESE("CHS");

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
        if (language.equalsIgnoreCase("NL")) {
            return DUTCH;
        } else if (language.equalsIgnoreCase("EN")) {
            return ENGLISH;
        } else if (language.equalsIgnoreCase("FR")) {
            return FRENCH;
        } else if (language.equalsIgnoreCase("DE")) {
            return GERMAN;
        } else if (language.equalsIgnoreCase("ES")) {
            return SPANISH;
        } else if (language.equalsIgnoreCase("RU")) {
            return RUSSIAN;
        } else if (language.equalsIgnoreCase("LV")) {
            return LATVIAN;
        } else if (language.equalsIgnoreCase("DK")) {
            return DANSK;
        } else if (language.equalsIgnoreCase("HU")) {
            return HUNGARIAN;
        } else if (language.equalsIgnoreCase("IT")) {
            return ITALIAN;
        } else if (language.equalsIgnoreCase("BG")) {
            return BULGARIAN;
        } else if (language.equalsIgnoreCase("CHS")) {
            return CHINESE;
        } else {
            return null;
        }
    }
}
