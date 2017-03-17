package me.smessie.MultiLanguage.bukkit;


import java.sql.SQLException;
import me.smessie.MultiLanguage.bukkit.commands.Sprog;
import me.smessie.MultiLanguage.bukkit.commands.Bulgarian;
import me.smessie.MultiLanguage.bukkit.commands.Chinese;
import me.smessie.MultiLanguage.bukkit.commands.English;
import me.smessie.MultiLanguage.bukkit.commands.Hungarian;
import me.smessie.MultiLanguage.bukkit.commands.Idioma;
import me.smessie.MultiLanguage.bukkit.commands.Italian;
import me.smessie.MultiLanguage.bukkit.commands.Langue;
import me.smessie.MultiLanguage.bukkit.commands.Russian;
import me.smessie.MultiLanguage.bukkit.commands.Sprache;
import me.smessie.MultiLanguage.bukkit.commands.Taal;
import me.smessie.MultiLanguage.bukkit.commands.Valoda;
import me.smessie.MultiLanguage.main.Languages;
import me.smessie.MultiLanguage.main.MySQL;
import me.smessie.MultiLanguage.main.Settings;
import me.smessie.MultiLanguage.bukkit.DataFile;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	@SuppressWarnings("unused")
	private static MySQL mysql;
	
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
		if(useMysql) {
			connectMysql();
			try {
				MySQL.createTable();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MySQL.disable();
		} else {
			DataFile.getInstance().setup(plugin);
		}
		Settings.mode = "Bukkit";
		Settings.useMysql = useMysql;
		Settings.table = getConfig().getString("mysql.table");
		Settings.createMysqlTable = getConfig().getBoolean("create-mysqlTable-ifNotExist");
		Settings.defaultLanguage = getConfig().getString("defaultLanguage");
		
		getCommand("language").setExecutor(new English());
		getCommand("langue").setExecutor(new Langue());
		getCommand("sprache").setExecutor(new Sprache());
		getCommand("taal").setExecutor(new Taal());
		getCommand("idioma").setExecutor(new Idioma());
		getCommand("язык").setExecutor(new Russian());
		getCommand("valoda").setExecutor(new Valoda());
		getCommand("Sprog").setExecutor(new Sprog());
		getCommand("език").setExecutor(new Bulgarian());
		getCommand("语言").setExecutor(new Chinese());
		getCommand("nyelv").setExecutor(new Hungarian());
		getCommand("lingua").setExecutor(new Italian());
		
		Languages.addSupportedLanguages();
				
	}
	public void onDisable() {
		plugin = null;
	}
	
	public void connectMysql(){
		
		ip = getConfig().getString("mysql.host");
		port = getConfig().getInt("mysql.port");
		username = getConfig().getString("mysql.user");
		password = getConfig().getString("mysql.password");
		db = getConfig().getString("mysql.database");
		
		mysql = new MySQL(ip, port, username, password, db);
				
	}

}
