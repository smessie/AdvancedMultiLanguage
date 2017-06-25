package me.smessie.MultiLanguage.bukkit.commands;

import java.sql.SQLException;

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

public class Valoda implements CommandExecutor {

    ChatColor red = ChatColor.RED;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("valoda")) {

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
                            player.sendMessage(ChatColor.GREEN + "Tava valoda ir iestatīta uz " + taal + " valodu.");

                            if (Implement.warnOnSelect(taal)) {
                                player.sendMessage(red + "Uzmanību! Jūs nevarat runāt " + taal + " čatā.");
                            }
                        } else {
                            player.sendMessage(red + "šī valoda ir desaktivēta! :(");
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
                            player.sendMessage(ChatColor.GREEN + "Tava valoda ir iestatīta uz " + taal + " valodu.");

                            if (Implement.warnOnSelect(taal)) {
                                player.sendMessage(red + "Uzmanību! Jūs nevarat runāt " + taal + " čatā.");
                            }
                        } else {
                            player.sendMessage(red + "šī valoda ir desaktivēta! :(");
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
                            player.sendMessage(ChatColor.GREEN + "Tava valoda ir iestatīta uz " + taal + " valodu.");

                            if (Implement.warnOnSelect(taal)) {
                                player.sendMessage(red + "Uzmanību! Jūs nevarat runāt " + taal + " čatā.");
                            }
                        } else {
                            player.sendMessage(red + "šī valoda ir desaktivēta! :(");
                        }
                    } else {
                        player.sendMessage(red + "valoda " + args[0] + " nav atrasta!");
                    }
                } else {
                    player.sendMessage(red + "Lietošana: /valoda <valoda>");
                }
            } else {
                sender.sendMessage(red + "Hé, Tikai Spēlē spēlētāji var iestatīt savu valodu! :o");
            }
        }
        return true;
    }

}

