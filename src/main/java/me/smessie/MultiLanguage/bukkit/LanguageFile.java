package me.smessie.MultiLanguage.bukkit;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class LanguageFile extends YamlConfiguration {
	
	private JavaPlugin plugin;
	private String fileName;
	private String dir;

	public LanguageFile(JavaPlugin plugin, String fileName, String dir){
		this.plugin = plugin;
		this.fileName = fileName + (fileName.endsWith(".yml") ? "" : ".yml");
		this.dir = dir;
		createFile();
	}

	private void createFile() {
		try {
			File file = new File(plugin.getDataFolder() + dir, fileName);
			if (!file.exists()){
				if(plugin.getResource(fileName) != null){
					plugin.saveResource(fileName, false);
				} else {
					save(file);
				}
			} else {
				load(file);
				save(file);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void save(){
		try {
			save(new File(plugin.getDataFolder() + dir, fileName));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}