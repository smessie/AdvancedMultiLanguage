package me.smessie.MultiLanguage.bukkit;

public class Implement {
	
	public static void setLanguageFile(String uuid, String language) {
		DataFile.getInstance().getData().set("players." + uuid, language);
		DataFile.getInstance().saveData();
	}
	
	public static String getLanguage(String uuid) {
		String language = DataFile.getInstance().getData().getString("players." + uuid);
		return language;
	}
	
	public static boolean languageEnabled(String language) {
		boolean bln = Main.plugin.getConfig().getBoolean("languages." + language);
		return bln;
	}
	
	public static boolean warnOnSelect(String language) {
		boolean bln = Main.plugin.getConfig().getBoolean("warn-on-select." + language);
		return bln;
	}
	
	public static boolean createMysqlTable() {
		boolean bln = Main.plugin.getConfig().getBoolean("create-mysqlTable-ifNotExist");
		return bln;
	}

}
