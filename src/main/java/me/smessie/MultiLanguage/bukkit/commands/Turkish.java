package me.smessie.MultiLanguage.bukkit.commands;

import me.smessie.MultiLanguage.api.Language;
import me.smessie.MultiLanguage.bukkit.ChangeLanguageEvent;
import me.smessie.MultiLanguage.bukkit.Implement;
import me.smessie.MultiLanguage.bukkit.Main;
import me.smessie.MultiLanguage.main.Cache;
import me.smessie.MultiLanguage.main.Languages;
import me.smessie.MultiLanguage.main.MySQL;
import me.smessie.MultiLanguage.main.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Turkish implements CommandExecutor {

    ChatColor red = ChatColor.RED;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("dil")) {

            if (sender instanceof Player) {

                Player player = (Player) sender;

                if (args.length == 1) {

                    String taal = args[0];

                    if (Languages.isSupportedLanguage(taal.toUpperCase())) {
                        String formatTaal = taal.toUpperCase();
                        if (Implement.languageEnabled(formatTaal)) {
                            ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
                            Bukkit.getPluginManager().callEvent(event);
                            if (event.isCancelled()) {
                                return true;
                            }
                            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                                if (Main.useMysql) {
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
                                Cache.setPlayerCachedLanguage(player.getUniqueId().toString(), Language.getLanguageFromString(formatTaal));
                            });
                            player.sendMessage(ChatColor.GREEN + "Dilin " + taal + "olarak değiştirildi.");

                            if (Implement.warnOnSelect(taal)) {
                                player.sendMessage(red + "Dikkat! Sohbette " + taal + " konuşamazsın.");
                            }
                        } else {
                            player.sendMessage(red + "Bu dil kullanıma kapalıdır! :(");
                        }
                    } else if (Languages.isSupportedLanguageFull(taal.toLowerCase())) {
                        String formatTaal = Languages.languagesFull.get(taal.toLowerCase());
                        if (Implement.languageEnabled(formatTaal)) {
                            ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
                            Bukkit.getPluginManager().callEvent(event);
                            if (event.isCancelled()) {
                                return true;
                            }
                            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                                if (Main.useMysql) {
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
                                Cache.setPlayerCachedLanguage(player.getUniqueId().toString(), Language.getLanguageFromString(formatTaal));
                            });
                            player.sendMessage(ChatColor.GREEN + "Dilin " + taal + "olarak değiştirildi.");

                            if (Implement.warnOnSelect(taal)) {
                                player.sendMessage(red + "Dikkat! Sohbette " + taal + " konuşamazsın.");
                            }
                        } else {
                            player.sendMessage(red + "Bu dil kullanıma kapalıdır! :(");
                        }
                    } else if (Languages.isSupportedLanguageOwn(taal.toLowerCase())) {
                        String formatTaal = Languages.languagesOwn.get(taal.toLowerCase());
                        if (Implement.languageEnabled(formatTaal)) {
                            ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
                            Bukkit.getPluginManager().callEvent(event);
                            if (event.isCancelled()) {
                                return true;
                            }
                            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                                if (Main.useMysql) {
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
                                Cache.setPlayerCachedLanguage(player.getUniqueId().toString(), Language.getLanguageFromString(formatTaal));
                            });
                            player.sendMessage(ChatColor.GREEN + "Dilin " + taal + "olarak değiştirildi.");

                            if (Implement.warnOnSelect(taal)) {
                                player.sendMessage(red + "Dikkat! Sohbette " + taal + " konuşamazsın.");
                            }
                        } else {
                            player.sendMessage(red + "Bu dil kullanıma kapalıdır! :(");
                        }
                    } else {
                        player.sendMessage(red + args[0] + " dili bulunamadı!");
                    }
                } else {
                    player.sendMessage(red + "Kullanim: /dil <dil>");
                }
            } else {
                sender.sendMessage(red + "Hé, Sadece oyunda olan kişiler kendi dillerini seçebilir! :o");
            }
        }
        return true;
    }

}
