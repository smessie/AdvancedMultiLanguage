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
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Russian implements CommandExecutor {
	
	ChatColor red = ChatColor.RED;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
		
		if(label.equalsIgnoreCase("язык")) {
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				if(args.length == 1) {
					
					String taal = args[0];
					
					if(Languages.isSupportedLanguage(taal.toUpperCase())) {
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
							player.sendMessage(ChatColor.GREEN + "Выбран язык " + taal + ".");
							
							if(Implement.warnOnSelect(taal)) {
								player.sendMessage(red + "Внимание! Вы не можете писать в чат на этом языке " + taal + ".");
							}
						} else {
							player.sendMessage(red + "Этот язык отключён! :(");
						}
					} else
					if(Languages.isSupportedLanguage(taal.toLowerCase())) {
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
							player.sendMessage(ChatColor.GREEN + "Выбран язык " + taal + ".");
							
							if(Implement.warnOnSelect(taal)) {
								player.sendMessage(red + "Внимание! Вы не можете писать в чат на этом языке " + taal + ".");
							}
						} else {
							player.sendMessage(red + "Этот язык отключён! :(");
						}
					} else
					if(Languages.isSupportedLanguage(taal.toLowerCase())) {
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
							player.sendMessage(ChatColor.GREEN + "Выбран язык " + taal + ".");
							
							if(Implement.warnOnSelect(taal)) {
								player.sendMessage(red + "Внимание! Вы не можете писать в чат на этом языке " + taal + ".");
							}
						} else {
							player.sendMessage(red + "Этот язык отключён! :(");
						}
					} else {
						player.sendMessage(red + "Язык " + args[0] + " не найден!");
					}
				} else {
					player.sendMessage(red + "Использование: /язык <язык>");
				}
			} else {
				sender.sendMessage(red + "Hé, Только игроки могут изменять свой язык! :o");
			}
		}
		return true;
	}

}
