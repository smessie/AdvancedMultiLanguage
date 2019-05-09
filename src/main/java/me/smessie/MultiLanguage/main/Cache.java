package me.smessie.MultiLanguage.main;

import me.smessie.MultiLanguage.api.Language;

import java.sql.SQLException;
import java.util.HashMap;

public class Cache {

    private static int caching = 7200000;

    private static HashMap<String, Language> cachedLanguage = new HashMap<>();
    private static HashMap<String, Long> lastCached = new HashMap<>();

    private static long getLastCached(String uuid) {
        return lastCached.get(uuid);
    }

    private static void setLastCached(String uuid, long milliseconds) {
        lastCached.put(uuid, milliseconds);
    }

    private static boolean isCached(String uuid) {
        return lastCached.containsKey(uuid);
    }

    private static void setCachedLanguage(String uuid, Language language) {
        cachedLanguage.put(uuid, language);
    }

    private static Language getCachedLanguage(String uuid) {
        return cachedLanguage.get(uuid);
    }

    public static void setCaching(int caching1) {
        caching = caching1;
    }

    public static Language getPlayerLanguage(String uuid) {
        if (isCached(uuid)) {
            if (getLastCached(uuid) + caching > System.currentTimeMillis()) {
                return getCachedLanguage(uuid);
            }
        }
        String languageString = null;
        if (Settings.useMysql) {
            try (MySQL mySQL = Settings.connectMysql()) {
                languageString = mySQL.getLanguage(uuid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            languageString = Settings.getLanguage(uuid);
        }
        if (languageString == null || languageString.equalsIgnoreCase("")) {
            languageString = Settings.defaultLanguage;
        }
        Language language = Language.getLanguageFromString(languageString);
        setCachedLanguage(uuid, language);
        setLastCached(uuid, System.currentTimeMillis());
        return language;
    }

    public static void setPlayerCachedLanguage(String uuid, Language language) {
        setCachedLanguage(uuid, language);
        setLastCached(uuid, System.currentTimeMillis());
    }
}
