package me.smessie.MultiLanguage.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Languages {
	
	public static List<String> languages = new ArrayList<String>();
	public static HashMap<String, String> languagesOwn = new HashMap<String, String>();
	public static HashMap<String, String> languagesFull = new HashMap<String, String>();
	
	public static void addSupportedLanguages() {
		languages.add("NL");	languagesOwn.put("nederlands", "NL");	languagesFull.put("dutch", "NL");
		languages.add("EN");	languagesOwn.put("english", "EN");		languagesFull.put("english", "EN");
		languages.add("FR");	languagesOwn.put("francais", "FR");		languagesFull.put("french", "FR");
		languages.add("DE");	languagesOwn.put("deutsch", "DE");		languagesFull.put("german", "DE");
		languages.add("ES");	languagesOwn.put("Español", "ES");		languagesFull.put("Spanish", "ES");
		languages.add("RU");	languagesOwn.put("Русский", "RU");		languagesFull.put("Russian", "RU");
		languages.add("LV");	languagesOwn.put("Latviešu", "LV");		languagesFull.put("Latvian", "LV");
		languages.add("DK");	languagesOwn.put("Denis", "DK");		languagesFull.put("Dansk", "DK");
		languages.add("HU");	languagesOwn.put("Magyar", "HU");		languagesFull.put("Hungarian", "HU");
		languages.add("IT");	languagesOwn.put("Italiano", "IT");		languagesFull.put("Italian", "IT");
		languages.add("BG");	languagesOwn.put("български", "BG");	languagesFull.put("Bulgarian", "BG");
	}

}
