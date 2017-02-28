package me.smessie.MultiLanguage.bukkit.commands;

import java.sql.SQLException;

import me.smessie.MultiLanguage.api.Language;
import me.smessie.MultiLanguage.bukkit.ChangeLanguageEvent;
import me.smessie.MultiLanguage.bukkit.Implement;
import me.smessie.MultiLanguage.bukkit.Main;
import me.smessie.MultiLanguage.main.Languages;
import me.smessie.MultiLanguage.main.MySQL;
import me.smessie.MultiLanguage.main.Settings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Bulgarian {
	
	ChatColor red = ChatColor.RED;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
		
		if(label.equalsIgnoreCase("език")) {
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				if(args.length == 1) {
					
					String taal = args[0];
					
					if(Languages.languages.contains(taal.toUpperCase())) {
						String formatTaal = taal.toUpperCase();
						if(Implement.languageEnabled(formatTaal)) {
							ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
							Bukkit.getPluginManager().callEvent(event);
							if(event.isCancelled()) {
								return true;
							}
							Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, new Runnable() {
								public void run() {
									if(Main.useMysql) {
										Settings.connectMysql();
										try {
											MySQL.setLanguageMysql(player.getUniqueId().toString(), formatTaal, player.getAddress().toString());
										} catch (SQLException e) {
											e.printStackTrace();
										}
										MySQL.disable();
									} else {
										Implement.setLanguageFile(player.getUniqueId().toString(), formatTaal);
									}
								}
							});
							player.sendMessage(ChatColor.GREEN + "Езикът Ви е сменен на " + taal + ".");
							
							if(Implement.warnOnSelect(taal)) {
								player.sendMessage(red + "Внимание, можете да говорите само на " + taal + " в чата.");
							}
						} else {
							player.sendMessage(red + "Езикът е недостъпен. Най-вероятно е бил изключен. :(");
						}
					} else 
					if(Languages.languagesFull.containsKey(taal.toLowerCase())) {
						String formatTaal = Languages.languagesFull.get(taal.toLowerCase());
						if(Implement.languageEnabled(formatTaal)) {
							ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
							Bukkit.getPluginManager().callEvent(event);
							if(event.isCancelled()) {
								return true;
							}
							Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, new Runnable() {
								public void run() {
									if(Main.useMysql) {
										Settings.connectMysql();
										try {
											MySQL.setLanguageMysql(player.getUniqueId().toString(), formatTaal, player.getAddress().toString());
										} catch (SQLException e) {
											e.printStackTrace();
										}
										MySQL.disable();
									} else {
										Implement.setLanguageFile(player.getUniqueId().toString(), formatTaal);
									}
								}
							});
							player.sendMessage(ChatColor.GREEN + "Езикът Ви е сменен на " + taal + ".");
							
							if(Implement.warnOnSelect(taal)) {
								player.sendMessage(red + "Внимание, можете да говорите само на " + taal + " в чата.");
							}
						} else {
							player.sendMessage(red + "Езикът е недостъпен. Най-вероятно е бил изключен. :(");
						}
					} else
					if(Languages.languagesOwn.containsKey(taal.toLowerCase())) {
						String formatTaal = Languages.languagesOwn.get(taal.toLowerCase());
						if(Implement.languageEnabled(formatTaal)) {
							ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
							Bukkit.getPluginManager().callEvent(event);
							if(event.isCancelled()) {
								return true;
							}
							Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, new Runnable() {
								public void run() {
									if(Main.useMysql) {
										Settings.connectMysql();
										try {
											MySQL.setLanguageMysql(player.getUniqueId().toString(), formatTaal, player.getAddress().toString());
										} catch (SQLException e) {
											e.printStackTrace();
										}
										MySQL.disable();
									} else {
										Implement.setLanguageFile(player.getUniqueId().toString(), formatTaal);
									}
								}
							});
							player.sendMessage(ChatColor.GREEN + "Езикът Ви е сменен на " + taal + ".");
							
							if(Implement.warnOnSelect(taal)) {
								player.sendMessage(red + "Внимание, можете да говорите само на " + taal + " в чата.");
							}
						} else {
							player.sendMessage(red + "Езикът е недостъпен. Най-вероятно е бил изключен. :(");
						}
					} else {
						player.sendMessage(red + args[0] + " недостъпен!");
					}
				} else {
					player.sendMessage(red + "Употреба: /език <език>");
				}
			} else {
				sender.sendMessage(red + "Hé, Само играчи могат да сменят езика си! :o");
			}
		}
		return true;
	}

}
