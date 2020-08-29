package me.smessie.MultiLanguage.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Languages {

	public static List<String> languages = new ArrayList<>();
	public static HashMap<String, String> languagesOwn = new HashMap<>();
	public static HashMap<String, String> languagesFull = new HashMap<>();

	public static void addSupportedLanguages() {
		languages.add("NL");	languagesOwn.put("nederlands", "NL");	languagesFull.put("dutch", "NL");
		languages.add("EN");	languagesOwn.put("english", "EN");	languagesFull.put("english", "EN");
		languages.add("FR");	languagesOwn.put("francais", "FR");	languagesFull.put("french", "FR");
		languages.add("DE");	languagesOwn.put("deutsch", "DE");	languagesFull.put("german", "DE");
		languages.add("ES");	languagesOwn.put("español", "ES");	languagesFull.put("spanish", "ES");
		languages.add("RU");	languagesOwn.put("pусский", "RU");	languagesFull.put("russian", "RU");
		languages.add("LV");	languagesOwn.put("latviešu", "LV");	languagesFull.put("latvian", "LV");
		languages.add("DK");	languagesOwn.put("denis", "DK");	languagesFull.put("dansk", "DK");
		languages.add("HU");	languagesOwn.put("magyar", "HU");	languagesFull.put("hungarian", "HU");
		languages.add("IT");	languagesOwn.put("italiano", "IT");	languagesFull.put("italian", "IT");
		languages.add("BG");	languagesOwn.put("български", "BG");	languagesFull.put("bulgarian", "BG");
		languages.add("CHS");	languagesOwn.put("chinois", "CHS");	languagesFull.put("chinese", "CHS");
		languages.add("PL");	languagesOwn.put("polski", "PL");	languagesFull.put("polish", "PL");
		languages.add("PT");	languagesOwn.put("português", "PT");	languagesFull.put("portuguese", "PT");
		languages.add("SLO");	languagesOwn.put("slovenija", "SLO");	languagesFull.put("slovenia", "SLO");
		languages.add("LT");	languagesOwn.put("lietuvių", "LT");	languagesFull.put("lithuanian", "LT");
		languages.add("TR");	languagesOwn.put("türkçe", "TR");	languagesFull.put("turkish", "TR");
		languages.add("SK");	languagesOwn.put("slovečina", "SK");	languagesFull.put("slovak", "SK");
		languages.add("CS");	languagesOwn.put("ceština", "CS");	languagesFull.put("czech", "CS");
		languages.add("KO");	languagesOwn.put("한국어", "KO");	languagesFull.put("korean", "KO");
	}

    public static boolean isSupportedLanguage(String language) {
        if (languages.isEmpty()) {
            addSupportedLanguages();
        }
        return languages.contains(language.toUpperCase());
    }

    public static boolean isSupportedLanguageOwn(String language) {
        if (languagesOwn.isEmpty()) {
            addSupportedLanguages();
        }
        return languagesOwn.containsKey(language.toLowerCase());
    }

    public static boolean isSupportedLanguageFull(String language) {
        if (languages.isEmpty()) {
            addSupportedLanguages();
        }
        return languagesFull.containsKey(language.toLowerCase());
    }

}
