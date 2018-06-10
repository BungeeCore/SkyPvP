package me.bungeecore.skypvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.bungeecore.skypvp.sql.MySQL;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		new MySQL().registerPlayer(p.getUniqueId());
		new MySQL().registerPlayer2(p.getUniqueId());
	}
}
