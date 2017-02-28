package me.smessie.MultiLanguage.bungeecord;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

public class DataFile {
   
    public static void setupConfig(Plugin p) {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
        File configFile = new File(p.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                try (InputStream is = p.getResourceAsStream("config.yml");
                     OutputStream os = new FileOutputStream(configFile)) {
                    ByteStreams.copy(is, os);
                }
            } catch (IOException e) {
                BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(ChatColor.RED + "Unable to create configuration file"));
            }
        }
    }
    
    public static void setupData(Plugin p) {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
        File configFile = new File(p.getDataFolder(), "data.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                try (InputStream is = p.getResourceAsStream("data.yml");
                     OutputStream os = new FileOutputStream(configFile)) {
                    ByteStreams.copy(is, os);
                }
            } catch (IOException e) {
                BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(ChatColor.RED + "Unable to create data file"));
            }
        }
    }
}

