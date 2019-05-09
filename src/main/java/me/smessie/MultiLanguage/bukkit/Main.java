package me.smessie.MultiLanguage.bukkit;


import me.smessie.MultiLanguage.bukkit.commands.English;
import me.smessie.MultiLanguage.main.Cache;
import me.smessie.MultiLanguage.main.Languages;
import me.smessie.MultiLanguage.main.MySQL;
import me.smessie.MultiLanguage.main.Settings;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Main extends JavaPlugin {

    public static Main plugin;

    public static String defaultLanguage;

    public static boolean useMysql;

    public static String ip = null;
    public static int port = 3306;
    public static String username = null;
    public static String password = null;
    public static String db = null;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();

        defaultLanguage = getConfig().getString("defaultLanguage");
        useMysql = getConfig().getBoolean("use-mysql");
        Settings.mode = "Bukkit";
        Settings.useMysql = useMysql;
        Settings.table = getConfig().getString("mysql.table");
        Settings.createMysqlTable = getConfig().getBoolean("create-mysqlTable-ifNotExist");
        Settings.defaultLanguage = getConfig().getString("defaultLanguage");
        if (useMysql) {
            try (MySQL mySQL = connectMysql()) {
                mySQL.createTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            DataFile.getInstance().setup(plugin);
        }

        getCommand("language").setExecutor(new English());

        Languages.addSupportedLanguages();

        int caching = getConfig().getInt("caching");
        if (caching > 0) {
            Cache.setCaching(caching);
        } else {
            getConfig().set("caching", 7200000);
            saveConfig();
            Cache.setCaching(7200000);
        }


    }

    public void onDisable() {
        plugin = null;
    }

    public MySQL connectMysql() {

        if (ip == null) {
            ip = getConfig().getString("mysql.host");
            port = getConfig().getInt("mysql.port");
            username = getConfig().getString("mysql.user");
            password = getConfig().getString("mysql.password");
            db = getConfig().getString("mysql.database");
        }

        return new MySQL(ip, port, username, password, db);
    }

}
