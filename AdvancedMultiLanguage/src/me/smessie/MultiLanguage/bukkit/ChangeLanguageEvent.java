package me.smessie.MultiLanguage.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.smessie.MultiLanguage.api.Language;

public class ChangeLanguageEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
	private Language language;
	private Player player;
	
	public ChangeLanguageEvent(Language language, Player player) {
		this.language = language;
		this.player = player;
	}
	public Language getLanguage() {
		return language;
	}
	public Player getPlayer() {
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
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	

}
