package me.smessie.MultiLanguage.main;

import me.smessie.MultiLanguage.bukkit.Main;

public class Settings {
	
	public static boolean useMysql;
	public static String table;
	public static boolean createMysqlTable;
	public static String mode;
	public static String defaultLanguage;
	
	public static void connectMysql() {
		if(mode.equalsIgnoreCase("Bukkit")) {
			Main.plugin.connectMysql();
		} else if(mode.equalsIgnoreCase("BungeeCord")) {
			me.smessie.MultiLanguage.bungeecord.Main.plugin.connectMysql();
		}
	}
	
	public static boolean languageEnabled(String language) {
		if(mode.equalsIgnoreCase("Bukkit")) {
			return me.smessie.MultiLanguage.bukkit.Implement.languageEnabled(language);
		} else if(mode.equalsIgnoreCase("BungeeCord")) {
			return me.smessie.MultiLanguage.bungeecord.Implement.languageEnabled(language);
		} else {
			return false;
		}
	}

	public static void setLanguageFile(String uuid, String taal) {
		if(mode.equalsIgnoreCase("Bukkit")) {
			me.smessie.MultiLanguage.bukkit.Implement.setLanguageFile(uuid, taal);
		} else if(mode.equalsIgnoreCase("BungeeCord")) {
			me.smessie.MultiLanguage.bungeecord.Implement.setLanguageFile(uuid, taal);
		}
	}
	
	public static String getLanguage(String uuid) {
		if(mode.equalsIgnoreCase("Bukkit")) {
			return me.smessie.MultiLanguage.bukkit.Implement.getLanguage(uuid);
		} else if(mode.equalsIgnoreCase("BungeeCord")) {
			return me.smessie.MultiLanguage.bungeecord.Implement.getLanguage(uuid);
		} else {
			return null;
		}
	}

}
