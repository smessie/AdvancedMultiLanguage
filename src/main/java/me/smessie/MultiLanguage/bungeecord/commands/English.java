package me.smessie.MultiLanguage.bungeecord.commands;

import me.smessie.MultiLanguage.api.Language;
import me.smessie.MultiLanguage.bungeecord.ChangeLanguageEvent;
import me.smessie.MultiLanguage.bungeecord.Implement;
import me.smessie.MultiLanguage.bungeecord.Main;
import me.smessie.MultiLanguage.main.Cache;
import me.smessie.MultiLanguage.main.Languages;
import me.smessie.MultiLanguage.main.MySQL;
import me.smessie.MultiLanguage.main.Settings;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class English extends Command {

    public English() {
        super("language", "", "langue", "sprache", "taal", "idioma", "язык", "valoda", "sprog", "език", "语言", "nyelv", "lingua", "język", "linguagem", "jezik", "kalba", "dil");
    }

    ChatColor red = ChatColor.RED;

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {

            final ProxiedPlayer player = (ProxiedPlayer) sender;

            ResourceBundle bundle = ResourceBundle.getBundle("translations/messages", player.getLocale());

            if (args.length == 1) {

                String language = args[0];

                if (Languages.isSupportedLanguage(language.toUpperCase())) {
                    final String formatTaal = language.toUpperCase();
                    handleSetLanguage(player, bundle, language, formatTaal);
                } else if (Languages.isSupportedLanguageFull(language.toLowerCase())) {
                    final String formatTaal = Languages.languagesFull.get(language.toLowerCase());
                    handleSetLanguage(player, bundle, language, formatTaal);
                } else if (Languages.isSupportedLanguageOwn(language.toLowerCase())) {
                    final String formatTaal = Languages.languagesOwn.get(language.toLowerCase());
                    handleSetLanguage(player, bundle, language, formatTaal);
                } else {
                    player.sendMessage(new TextComponent(red + MessageFormat.format(bundle.getString("not-found"), language)));
                }
            } else {
                player.sendMessage(new TextComponent(red + bundle.getString("usage")));
            }
        } else {
            sender.sendMessage(new TextComponent(red + ResourceBundle.getBundle("translations/messages").getString("only-ingame")));
        }
    }

    private void handleSetLanguage(ProxiedPlayer player, ResourceBundle bundle, String language, String formatTaal) {
        if (Implement.languageEnabled(formatTaal)) {
            ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
            ProxyServer.getInstance().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
            ProxyServer.getInstance().getScheduler().runAsync(Main.plugin, () -> {
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
            player.sendMessage(new TextComponent(ChatColor.GREEN + MessageFormat.format(bundle.getString("language-set"), language)));

            if (Implement.warnOnSelect(language)) {
                player.sendMessage(new TextComponent(red + MessageFormat.format(bundle.getString("may-not-speak"), language)));
            }
        } else {
            player.sendMessage(new TextComponent(red + bundle.getString("language-disabled")));
        }
    }

}
