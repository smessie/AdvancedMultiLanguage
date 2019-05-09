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
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class English implements CommandExecutor {

    ChatColor red = ChatColor.RED;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            ResourceBundle bundle = ResourceBundle.getBundle("translations/messages", Locale.forLanguageTag(player.spigot().getLocale()));

            if (args.length == 1) {

                String language = args[0];

                if (Languages.isSupportedLanguage(language.toUpperCase())) {
                    String formatTaal = language.toUpperCase();
                    if (handleSetLanguage(player, bundle, language, formatTaal)) return true;
                } else if (Languages.isSupportedLanguageFull(language.toLowerCase())) {
                    String formatTaal = Languages.languagesFull.get(language.toLowerCase());
                    if (handleSetLanguage(player, bundle, language, formatTaal)) return true;
                } else if (Languages.isSupportedLanguageOwn(language.toLowerCase())) {
                    String formatTaal = Languages.languagesOwn.get(language.toLowerCase());
                    if (handleSetLanguage(player, bundle, language, formatTaal)) return true;
                } else {
                    player.sendMessage(red + MessageFormat.format(bundle.getString("not-found"), language));
                }
            } else {
                player.sendMessage(red + bundle.getString("usage"));
            }
        } else {
            sender.sendMessage(red + ResourceBundle.getBundle("translations/messages").getString("only-ingame"));
        }
        return true;
    }

    private boolean handleSetLanguage(Player player, ResourceBundle bundle, String language, String formatTaal) {
        if (Implement.languageEnabled(formatTaal)) {
            ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return true;
            }
            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                if (Main.useMysql) {
                    try (MySQL mySQL = Settings.connectMysql()) {
                        mySQL.setLanguageMysql(player.getUniqueId().toString(), formatTaal, player.getAddress().toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    Implement.setLanguageFile(player.getUniqueId().toString(), formatTaal);
                }
                Cache.setPlayerCachedLanguage(player.getUniqueId().toString(), Language.getLanguageFromString(formatTaal));
            });
            player.sendMessage(ChatColor.GREEN + MessageFormat.format(bundle.getString("language-set"), language));

            if (Implement.warnOnSelect(language)) {
                player.sendMessage(red + MessageFormat.format(bundle.getString("may-not-speak"), language));
            }
        } else {
            player.sendMessage(red + bundle.getString("language-disabled"));
        }
        return false;
    }

}
