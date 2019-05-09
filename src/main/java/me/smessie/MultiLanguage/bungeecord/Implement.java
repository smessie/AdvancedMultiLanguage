package me.smessie.MultiLanguage.bungeecord;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Implement {

    public static void setLanguageFile(String uuid, String language) {
        File dataFile = new File("plugins/AdvancedMultiLanguage", "data.yml");
        Configuration data = null;
        try {
            data = YamlConfiguration.getProvider(YamlConfiguration.class).load(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        data.set("players." + uuid, language);

        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(data, new File("plugins/AdvancedMultiLanguage", "data.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLanguage(String uuid) {

        File dataFile = new File("plugins/AdvancedMultiLanguage", "data.yml");
        Configuration data = null;
        try {
            data = YamlConfiguration.getProvider(YamlConfiguration.class).load(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String language = data.getString("players." + uuid);
        return language;
    }

    public static boolean languageEnabled(String language) {

        File configFile = new File("plugins/AdvancedMultiLanguage", "config.yml");
        Configuration config = null;
        try {
            config = YamlConfiguration.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean bln = config.getBoolean("languages." + language);
        return bln;
    }

    public static boolean warnOnSelect(String language) {
        File configFile = new File("plugins/AdvancedMultiLanguage", "config.yml");
        Configuration config = null;
        try {
            config = YamlConfiguration.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean bln = config.getBoolean("warn-on-select." + language);
        return bln;
    }

    public static boolean createMysqlTable() {

        File configFile = new File("plugins/AdvancedMultiLanguage", "config.yml");
        Configuration config = null;
        try {
            config = YamlConfiguration.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean bln = config.getBoolean("create-mysqlTable-ifNotExist");
        return bln;
    }


}
