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

public class Chinese implements CommandExecutor {
	
	ChatColor red = ChatColor.RED;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
		
		if(label.equalsIgnoreCase("语言")) {
			
			if(sender instanceof Player) {
				
				final Player player = (Player) sender;
				
				if(args.length == 1) {
					
					String taal = args[0];
					
					if(Languages.isSupportedLanguage(taal.toUpperCase())) {
						final String formatTaal = taal.toUpperCase();
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
							player.sendMessage(ChatColor.GREEN + "您的语言设置为 " + taal + ".");
							
							if(Implement.warnOnSelect(taal)) {
								player.sendMessage(red + "注意！你可能不会在聊天中说英语  " + taal + ".");
							}
						} else {
							player.sendMessage(red + "此语言已禁用。 :(");
						}
					} else 
					if(Languages.isSupportedLanguage(taal.toLowerCase())) {
						final String formatTaal = Languages.languagesFull.get(taal.toLowerCase());
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
							player.sendMessage(ChatColor.GREEN + "您的语言设置为 " + taal + ".");
							
							if(Implement.warnOnSelect(taal)) {
								player.sendMessage(red + "注意！你可能不会在聊天中说英语 " + taal + ".");
							}
						} else {
							player.sendMessage(red + "此语言已禁用。 :(");
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
							player.sendMessage(ChatColor.GREEN + "您的语言设置为 " + taal + ".");
							
							if(Implement.warnOnSelect(taal)) {
								player.sendMessage(red + "注意！你可能不会在聊天中说英语 " + taal + ".");
							}
						} else {
							player.sendMessage(red + "此语言已禁用。 :(");
						}
					} else {
						player.sendMessage(red + "未找到语言 " + args[0] + "。");
					}
				} else {
					player.sendMessage(red + "用法：/语言<语言>");
				}
			} else {
				sender.sendMessage(red + "只有游戏玩家才能设置他们的语言。 :o");
			}
		}
		return true;
	}

}
