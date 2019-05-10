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
        switch (language) {
            case "NL":
                return DUTCH;
            case "EN":
                return ENGLISH;
            case "FR":
                return FRENCH;
            case "DE":
                return GERMAN;
            case "ES":
                return SPANISH;
            case "RU":
                return RUSSIAN;
            case "LV":
                return LATVIAN;
            case "DK":
                return DANSK;
            case "HU":
                return HUNGARIAN;
            case "IT":
                return ITALIAN;
            case "BG":
                return BULGARIAN;
            case "CHS":
                return CHINESE;
            default:
                return null;
        }
    }
}
