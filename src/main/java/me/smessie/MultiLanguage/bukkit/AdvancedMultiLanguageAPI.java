package me.smessie.MultiLanguage.bukkit;

import java.io.File;
import java.sql.SQLException;
import java.util.logging.Logger;

import me.smessie.MultiLanguage.main.Cache;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import me.smessie.MultiLanguage.api.Language;
import me.smessie.MultiLanguage.bukkit.Main;
import me.smessie.MultiLanguage.main.Languages;
import me.smessie.MultiLanguage.main.MySQL;
import me.smessie.MultiLanguage.main.Settings;

public class AdvancedMultiLanguageAPI {
	
	private static final Logger LOGGER = Logger.getLogger(AdvancedMultiLanguageAPI.class.getName());

	/**
	 * Get the language of a player by his uuid
	 * @uuid uuid the player's uuid in String format
	 * @return NL, EN, FR, DE, ES, RU, LV, DK
	 */
	public static String getLanguageOfUuid(String uuid) {
		return Cache.getPlayerLanguage(uuid).toString();
	}
	
	/**
	 * Get a translated message as set in the translation files
	 * @param uuid the player's uuid in String format
	 * @param path The place in the file where the translation is located
	 * @param plugin Name of your plugin in String format
	 * @return Translated message
	 */
	public static String getMessage(String uuid, String path, String plugin) {
		String language = getLanguageOfUuid(uuid);
		File f = new File("plugins/" + plugin + "/messages/" + language + ".yml");
		if(!f.exists()) {
			String defaultLanguage = Settings.defaultLanguage;
			f = new File("plugins/" + plugin + "/messages/" + defaultLanguage + ".yml");
			if(!f.exists()) {
				return null;
			}
		}
		YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
		
		String message = yamlFile.getString(path);
		
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	/**
	 * Set the language of a player
	 * @param uuid The player's uuid in String format
	 * @param language Must be one of this: NL, EN, FR, DE, ES, RU, LV, DK
	 */
	public static void setPlayerLanguage(String uuid, String language) {
		if(Languages.isSupportedLanguage(language.toUpperCase())) {
			String taal = language.toUpperCase();
			if(Settings.languageEnabled(taal)) {
				Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, new Runnable() {
					public void run() {
						if(Settings.useMysql) {
							Settings.connectMysql();
							try {
								MySQL.setLanguageMysql(uuid, taal);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							MySQL.disable();
						} else {
							Settings.setLanguageFile(uuid, taal);
						}
						Cache.setPlayerCachedLanguage(uuid, Language.getLanguageFromString(taal));
					}
				});
			} else {
				LOGGER.severe("A plugin using AML's API tried to set a players language to a disabled (" + language + ") language!");
			}
		} else {
			LOGGER.severe("A plugin using AML's API tried to set a players language to an unknown (" + language + ") language!");
		}
	}
	
	/**
	 * Set the language of a player
	 * @param uuid The player's uuid in String format
	 * @param language Language 
	 */
	public static void setPlayerLanguage(String uuid, Language language) {
		String taal = language.toString();
		if(Languages.isSupportedLanguage(taal.toUpperCase())) {
			if(Settings.languageEnabled(taal)) {
				Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, new Runnable() {
					public void run() {
						if(Settings.useMysql) {
							Settings.connectMysql();
							try {
								MySQL.setLanguageMysql(uuid, taal);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							MySQL.disable();
						} else {
							Settings.setLanguageFile(uuid, taal);
						}
                        Cache.setPlayerCachedLanguage(uuid, language);
					}
				});
			} else {
				LOGGER.severe("A plugin using AML's API tried to set a players language to a disabled (" + language + ") language!");
			}
		} else {
			LOGGER.severe("A plugin using AML's API tried to set a players language to an unknown (" + language + ") language!");
		}
	}
}
