package me.smessie.MultiLanguage.bungeecord;

import org.bukkit.event.HandlerList;

import me.smessie.MultiLanguage.api.Language;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

public class ChangeLanguageEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	private ProxiedPlayer player;
	private Language language;
	private boolean cancelled;
	public ChangeLanguageEvent(Language language, ProxiedPlayer player) {
		this.player = player;
		this.language = language;
	}
	public Language getLanguage() {
		return language;
	}
	public ProxiedPlayer getPlayer() {
		return player;
	}
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
	public HandlerList getHandlers() {
		return handlers;
	}

	
}
