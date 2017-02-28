package me.smessie.MultiLanguage.bungeecord.commands;

import java.sql.SQLException;

import me.smessie.MultiLanguage.api.Language;
import me.smessie.MultiLanguage.bungeecord.ChangeLanguageEvent;
import me.smessie.MultiLanguage.bungeecord.Implement;
import me.smessie.MultiLanguage.bungeecord.Main;
import me.smessie.MultiLanguage.main.Languages;
import me.smessie.MultiLanguage.main.MySQL;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Sprache extends Command {
	
	public Sprache() {
		super("sprache");
	}
	
	ChatColor red = ChatColor.RED;
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			
			ProxiedPlayer player = (ProxiedPlayer) sender;
			
			if(args.length == 1) {
				
				String taal = args[0];
				
				if(Languages.languages.contains(taal.toUpperCase())) {
					String formatTaal = taal.toUpperCase();
					if(Implement.languageEnabled(formatTaal)) {
						ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
						BungeeCord.getInstance().getPluginManager().callEvent(event);
						if(event.isCancelled()) {
							return;
						}
						BungeeCord.getInstance().getScheduler().runAsync(Main.plugin, new Runnable() {
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
						player.sendMessage(new TextComponent(red + "Diese Sprache ist außer Gefecht! :("));
					}
				} else
				if(Languages.languagesFull.containsKey(taal.toLowerCase())) {
					String formatTaal = Languages.languagesFull.get(taal.toLowerCase());
					if(Implement.languageEnabled(formatTaal)) {
						ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
						BungeeCord.getInstance().getPluginManager().callEvent(event);
						if(event.isCancelled()) {
							return;
						}
						BungeeCord.getInstance().getScheduler().runAsync(Main.plugin, new Runnable() {
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
						player.sendMessage(new TextComponent(red + "Diese Sprache ist außer Gefecht! :("));
					}
				} else
				if(Languages.languagesOwn.containsKey(taal.toLowerCase())) {
					String formatTaal = Languages.languagesOwn.get(taal.toLowerCase());
					if(Implement.languageEnabled(formatTaal)) {
						ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
						BungeeCord.getInstance().getPluginManager().callEvent(event);
						if(event.isCancelled()) {
							return;
						}
						BungeeCord.getInstance().getScheduler().runAsync(Main.plugin, new Runnable() {
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
						player.sendMessage(new TextComponent(red + "Diese Sprache ist außer Gefecht! :("));
					}
				} else {
					player.sendMessage(new TextComponent(red + "Sprache " + args[0] + " nicht gefunden!"));
				}
			} else {
				player.sendMessage(new TextComponent(red + "Gebrauch: /sprache <sprache>"));
			}
		} else {
			sender.sendMessage(new TextComponent(red + "Hé, nur ingame Spieler können ihre Sprache einstellen! :o"));
		}
	}

}