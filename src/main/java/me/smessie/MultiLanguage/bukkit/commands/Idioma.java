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

public class Idioma implements CommandExecutor {

    ChatColor red = ChatColor.RED;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("idioma")) {

            if (sender instanceof Player) {

                final Player player = (Player) sender;

                if (args.length == 1) {

                    String taal = args[0];

                    if (Languages.isSupportedLanguage(taal.toUpperCase())) {
                        final String formatTaal = taal.toUpperCase();
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
                            });
                            player.sendMessage(ChatColor.GREEN + "Tu idioma seleccionado es el " + taal + ".");

                            if (Implement.warnOnSelect(taal)) {
                                player.sendMessage(red + "Atención! No debes hablar en " + taal + " en el chat.");
                            }

                        } else {
                            player.sendMessage(red + "Este idioma se encuentra deshabilitado! :(");
                        }
                    } else if (Languages.isSupportedLanguageFull(taal.toLowerCase())) {
                        final String formatTaal = Languages.languagesFull.get(taal.toLowerCase());
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
                            });
                            player.sendMessage(ChatColor.GREEN + "Tu idioma seleccionado es el " + taal + ".");

                            if (Implement.warnOnSelect(taal)) {
                                player.sendMessage(red + "Atención! No debes hablar en " + taal + " en el chat.");
                            }
                        } else {
                            player.sendMessage(red + "Este idioma se encuentra deshabilitado! :(");
                        }
                    } else if (Languages.isSupportedLanguageOwn(taal.toLowerCase())) {
                        final String formatTaal = Languages.languagesOwn.get(taal.toLowerCase());
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
                            });
                            player.sendMessage(ChatColor.GREEN + "Tu idioma seleccionado es el " + taal + ".");

                            if (Implement.warnOnSelect(taal)) {
                                player.sendMessage(red + "Atención! No debes hablar en " + taal + " en el chat.");
                            }
                        } else {
                            player.sendMessage(red + "Este idioma se encuentra deshabilitado! :(");
                        }
                    } else {
                        player.sendMessage(red + "No se ha encontrado el idioma " + args[0] + "!");
                    }
                } else {
                    player.sendMessage(red + "Uso: /idioma <idioma>");
                }
            } else {
                sender.sendMessage(red + "Hé, solo los jugadores pueden modificar su idioma! :o");
            }
        }
        return true;
    }

}
