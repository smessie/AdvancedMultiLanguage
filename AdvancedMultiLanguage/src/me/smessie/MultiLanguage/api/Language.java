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
	BULGARIAN("BG");
	
	private final String language;
	
	Language(String language) {
		this.language = language;
	}
	/**
	 * Get the Language parsed in String format
	 * @return abbreviation of the language
	 */
	public String toString() {
		return language;
	}
	/**
	 * Parse a language in String format to the Language object
	 * @param language A language in String format
	 * @return The Language
	 */
	public static Language getLanguageFromString(String language) {
		if(Languages.languagesFull.containsKey(language)) {
			language = Languages.languagesFull.get(language);
		} else if(Languages.languagesOwn.containsKey(language)) {
			language = Languages.languagesOwn.get(language);
		}
		if(language.equalsIgnoreCase("NL")) {
			return DUTCH;
		} else if(language.equalsIgnoreCase("EN")) {
			return ENGLISH;
		} else if(language.equalsIgnoreCase("FR")) {
			return FRENCH;
		} else if(language.equalsIgnoreCase("DE")) {
			return GERMAN;
		} else if(language.equalsIgnoreCase("ES")) {
			return SPANISH;
		} else if(language.equalsIgnoreCase("RU")) {
			return RUSSIAN;
		} else if(language.equalsIgnoreCase("LV")) {
			return LATVIAN;
		} else if(language.equalsIgnoreCase("DK")) {
			return DANSK;
		} else if(language.equalsIgnoreCase("HU")) {
			return HUNGARIAN;
		} else if(language.equalsIgnoreCase("IT")) {
			return ITALIAN;
		} else if(language.equalsIgnoreCase("BG")) {
			return BULGARIAN;
		} else {
			return null;
		}
	}
	
	
	//private static String language;
	//public static Language DUTCH;
	
	/*public String toString() {
		return language;
	}
	
	public static void DUTCH() {
		language = "NL";
	}
	
	public static void ENGLISH() {
		language = "EN";
	}
	
	public static void FRENCH() {
		language = "FR";
	}
	
	public static void GERMAN() {
		language = "DE";
	}
	
	public static void SPANISH() {
		language = "ES";
	}
	
	public static void RUSSIAN() {
		language = "RU";
	}
	
	public static void LATVIAN() {
		language = "LV";
	}
	
	public static void DANSK() {
		language = "DK";
	}
	
	public static void HUNGARIAN() {
		language = "HU";
	}
	
	public static void ITALIAN() {
		language = "IT";
	}
	
	public static void BULGARIAN() {
		language = "BG";
	}*/

}
