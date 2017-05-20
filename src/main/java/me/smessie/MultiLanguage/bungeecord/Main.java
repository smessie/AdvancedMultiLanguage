package me.smessie.MultiLanguage.bungeecord;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import me.smessie.MultiLanguage.bungeecord.commands.*;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;
import me.smessie.MultiLanguage.main.Languages;
import me.smessie.MultiLanguage.main.MySQL;
import me.smessie.MultiLanguage.main.Settings;

public class Main extends Plugin {
	
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

	public void onEnable() {
		plugin = this;
		
		DataFile.setupConfig(plugin);
		
		File configFile = new File("plugins/AdvancedMultiLanguage", "config.yml");
		Configuration config = null;
		try {
			config = YamlConfiguration.getProvider(YamlConfiguration.class).load(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		defaultLanguage = config.getString("defaultLanguage");
		useMysql = config.getBoolean("use-mysql");
		if(useMysql) {
			connectMysql();
			try {
				MySQL.createTable();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MySQL.disable();
		} else {
			DataFile.setupData(plugin);
		}
		Settings.mode = "BungeeCord";
		Settings.useMysql = useMysql;
		Settings.table = config.getString("mysql.table");
		Settings.createMysqlTable = config.getBoolean("create-mysqlTable-ifNotExist");
		Settings.defaultLanguage = config.getString("defaultLanguage");

		ProxyServer.getInstance().getPluginManager().registerCommand(this, new English());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Langue());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Taal());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Sprache());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Idioma());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Russian());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Valoda());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Sprog());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Bulgarian());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Hungarian());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Italian());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Polish());

		Languages.addSupportedLanguages();
	}
	
	public void onDisable() {
		plugin = null;
	}
	
	public void connectMysql(){
		
		File configFile = new File("plugins/AdvancedMultiLanguage", "config.yml");
		Configuration config = null;
		try {
			config = YamlConfiguration.getProvider(YamlConfiguration.class).load(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ip = config.getString("mysql.host");
		port = config.getInt("mysql.port");
		username = config.getString("mysql.user");
		password = config.getString("mysql.password");
		db = config.getString("mysql.database");
		
		mysql = new MySQL(ip, port, username, password, db);
		
		System.out.println("Succesfully connected to mysql database!");
		
	}

}