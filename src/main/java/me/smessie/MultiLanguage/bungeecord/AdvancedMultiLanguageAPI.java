package me.smessie.MultiLanguage.bungeecord;

import me.smessie.MultiLanguage.api.Language;
import me.smessie.MultiLanguage.main.Cache;
import me.smessie.MultiLanguage.main.Languages;
import me.smessie.MultiLanguage.main.MySQL;
import me.smessie.MultiLanguage.main.Settings;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AdvancedMultiLanguageAPI {

    private static final Logger LOGGER = Logger.getLogger(AdvancedMultiLanguageAPI.class.getName());

    /**
     * Get the language of a player by his uuid
     * Output: NL, EN, FR, DE, ES, RU, LV, DK
     *
     * @return
     * @uuid uuid the player's uuid in String format
     */
    public static String getLanguageOfUuid(String uuid) {
        return Cache.getPlayerLanguage(uuid).toString();
    }

    /**
     * Get a translated message as set in the translation files
     *
     * @param uuid   the player's uuid in String format
     * @param path   The place in the file where the translation is located
     * @param plugin Name of your plugin in String format
     * @return
     */
    public static String getMessage(String uuid, String path, String plugin) {
        String language = getLanguageOfUuid(uuid);
        File f = new File("plugins/" + plugin + "/messages/" + language + ".yml");
        if (!f.exists()) {
            String defaultLanguage = Settings.defaultLanguage;
            f = new File("plugins/" + plugin + "/messages/" + defaultLanguage + ".yml");
            if (!f.exists()) {
                return null;
            }
        }
        Configuration yamlFile = null;
        try {
            yamlFile = YamlConfiguration.getProvider(YamlConfiguration.class).load(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String message = yamlFile.getString(path);

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Set the language of a player
     *
     * @param uuid     The player's uuid in String format
     * @param language Must be one of this: NL, EN, FR, DE, ES, RU, LV, DK
     */
    public static void setPlayerLanguage(final String uuid, String language) {
        if (Languages.isSupportedLanguage(language.toUpperCase())) {
            final String taal = language.toUpperCase();
            if (Settings.languageEnabled(taal)) {
                ProxyServer.getInstance().getScheduler().runAsync(Main.plugin, () -> {
                    if (Settings.useMysql) {
                        try (MySQL mySQL = Settings.connectMysql()) {
                            mySQL.setLanguageMysql(uuid, taal);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Settings.setLanguageFile(uuid, taal);
                    }
                    Cache.setPlayerCachedLanguage(uuid, Language.getLanguageFromString(taal));
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
     *
     * @param uuid     The player's uuid in String format
     * @param language Language
     */
    public static void setPlayerLanguage(final String uuid, Language language) {
        final String taal = language.toString();
        if (Languages.isSupportedLanguage(taal.toUpperCase())) {
            if (Settings.languageEnabled(taal)) {
                ProxyServer.getInstance().getScheduler().runAsync(Main.plugin, () -> {
                    if (Settings.useMysql) {
                        try (MySQL mySQL = Settings.connectMysql()) {
                            mySQL.setLanguageMysql(uuid, taal);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Settings.setLanguageFile(uuid, taal);
                    }
                    Cache.setPlayerCachedLanguage(uuid, language);
                });
            } else {
                LOGGER.severe("A plugin using AML's API tried to set a players language to a disabled (" + language + ") language!");
            }
        } else {
            LOGGER.severe("A plugin using AML's API tried to set a players language to an unknown (" + language + ") language!");
        }
    }
}
