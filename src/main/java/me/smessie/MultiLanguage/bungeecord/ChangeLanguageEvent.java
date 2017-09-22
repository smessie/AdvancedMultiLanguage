package me.smessie.MultiLanguage.bungeecord;

import me.smessie.MultiLanguage.api.Language;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

public class ChangeLanguageEvent extends Event implements Cancellable {
	
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

	
}
