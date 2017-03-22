package me.smessie.MultiLanguage.bungeecord.commands;

import java.sql.SQLException;

import me.smessie.MultiLanguage.api.Language;
import me.smessie.MultiLanguage.bungeecord.ChangeLanguageEvent;
import me.smessie.MultiLanguage.bungeecord.Implement;
import me.smessie.MultiLanguage.bungeecord.Main;
import me.smessie.MultiLanguage.main.Languages;
import me.smessie.MultiLanguage.main.MySQL;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Bulgarian extends Command {
	
	public Bulgarian() {
		super("език");
	}
	
	ChatColor red = ChatColor.RED;
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			
			final ProxiedPlayer player = (ProxiedPlayer) sender;
			
			if(args.length == 1) {
				
				String taal = args[0];
				
				if(Languages.isSupportedLanguage(taal.toUpperCase())) {
					final String formatTaal = taal.toUpperCase();
					if(Implement.languageEnabled(formatTaal)) {	
						ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(taal), player);
						ProxyServer.getInstance().getPluginManager().callEvent(event);
						if(event.isCancelled()) {
							return;
						}
						ProxyServer.getInstance().getScheduler().runAsync(Main.plugin, new Runnable() {
							public void run() {
								if(Main.useMysql) {
									try {
										MySQL.setLanguageMysql(player.getUniqueId().toString(), formatTaal, player.getAddress().toString());
									} catch (SQLException e) {
										e.printStackTrace();
									}
								} else {
									Implement.setLanguageFile(player.getUniqueId().toString(), formatTaal);
								}
							}
						});
						
						player.sendMessage(new TextComponent(ChatColor.GREEN + "Езикът Ви е сменен на " + taal + "."));
						
						if(Implement.warnOnSelect(taal)) {
							player.sendMessage(new TextComponent(red + "Внимание, можете да говорите само на " + taal + " в чата."));
						}
					} else {
						player.sendMessage(new TextComponent(red + "Езикът е недостъпен. Най-вероятно е бил изключен. :("));
					}
				} else 
				if(Languages.isSupportedLanguage(taal.toLowerCase())) {
					final String formatTaal = Languages.languagesFull.get(taal.toLowerCase());
					if(Implement.languageEnabled(formatTaal)) {
						ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
						ProxyServer.getInstance().getPluginManager().callEvent(event);
						if(event.isCancelled()) {
							return;
						}
						ProxyServer.getInstance().getScheduler().runAsync(Main.plugin, new Runnable() {
							public void run() {
								if(Main.useMysql) {
									try {
										MySQL.setLanguageMysql(player.getUniqueId().toString(), formatTaal, player.getAddress().toString());
									} catch (SQLException e) {
										e.printStackTrace();
									}
								} else {
									Implement.setLanguageFile(player.getUniqueId().toString(), formatTaal);
								}
							}
						});
						player.sendMessage(new TextComponent(ChatColor.GREEN + "Езикът Ви е сменен на " + taal + "."));
						
						if(Implement.warnOnSelect(taal)) {
							player.sendMessage(new TextComponent(red + "Внимание, можете да говорите само на " + taal + " в чата."));
						}
					} else {
						player.sendMessage(new TextComponent(red + "Езикът е недостъпен. Най-вероятно е бил изключен. :("));
					}
				} else
				if(Languages.isSupportedLanguage(taal.toLowerCase())) {
					final String formatTaal = Languages.languagesOwn.get(taal.toLowerCase());
					if(Implement.languageEnabled(formatTaal)) {						
						ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
						ProxyServer.getInstance().getPluginManager().callEvent(event);
						if(event.isCancelled()) {
							return;
						}
						ProxyServer.getInstance().getScheduler().runAsync(Main.plugin, new Runnable() {
							public void run() {
								if(Main.useMysql) {
									try {
										MySQL.setLanguageMysql(player.getUniqueId().toString(), formatTaal, player.getAddress().toString());
									} catch (SQLException e) {
										e.printStackTrace();
									}
								} else {
									Implement.setLanguageFile(player.getUniqueId().toString(), formatTaal);
								}
							}
						});
						player.sendMessage(new TextComponent(ChatColor.GREEN + "Езикът Ви е сменен на " + taal + "."));
						
						if(Implement.warnOnSelect(taal)) {
							player.sendMessage(new TextComponent(red + "Внимание, можете да говорите само на " + taal + " в чата."));
						}
					} else {
						player.sendMessage(new TextComponent(red + "Езикът е недостъпен. Най-вероятно е бил изключен. :("));
					}
				} else {
					player.sendMessage(new TextComponent(red + args[0] + " недостъпен!"));
				}
			} else {
				player.sendMessage(new TextComponent(red + "Употреба: /език <език>"));
			}
		} else {
			sender.sendMessage(new TextComponent(red + "Hé, Само играчи могат да сменят езика си! :o"));
		}
	}

}
