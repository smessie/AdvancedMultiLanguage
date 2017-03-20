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

@SuppressWarnings("LossyEncoding")
public class Langue implements CommandExecutor {
	
	ChatColor red = ChatColor.RED;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
		
		if(label.equalsIgnoreCase("langue")) {
			
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
							Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
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
							});
							player.sendMessage(ChatColor.GREEN + "Votre langue est définie sur " + taal + ".");
							
							if(Implement.warnOnSelect(taal)) {
								player.sendMessage(red + "Attention! Tu ne dois pas parler du " + taal + " dans le chat.");
							}
						} else {
							player.sendMessage(red + "Cette langue est inactivé! :(");
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
							Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
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
							});
							player.sendMessage(ChatColor.GREEN + "Votre langue est définie sur " + taal + ".");
							
							if(Implement.warnOnSelect(taal)) {
								player.sendMessage(red + "Attention! Tu ne dois pas parler du " + taal + " dans le chat.");
							}
						} else {
							player.sendMessage(red + "Cette langue est inactivé! :(");
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
							Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
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
							});
							player.sendMessage(ChatColor.GREEN + "Votre langue est définie sur " + taal + ".");
							
							if(Implement.warnOnSelect(taal)) {
								player.sendMessage(red + "Attention! Tu ne dois pas parler du " + taal + " dans le chat.");
							}
						} else {
							player.sendMessage(red + "Cette langue est inactivé! :(");
						}
					} else {
						player.sendMessage(red + "Langue " + args[0] + " pas trouvé!");
					}
				} else {
					player.sendMessage(red + "Emploi: /langue <langue>");
				}
			} else {
				sender.sendMessage(red + "H?, Seuls les joueurs ingame peuvent définir la langue! :o");
			}
		}
		return true;
	}

}

