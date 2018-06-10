package me.bungeecore.skypvp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.bungeecore.skypvp.listener.Cases;
import me.bungeecore.skypvp.listener.FreeSigns;
import me.bungeecore.skypvp.listener.PlayerJoinListener;
import me.bungeecore.skypvp.listener.addticket;
import me.bungeecore.skypvp.sql.MySQL;

public class SkyPvP extends JavaPlugin {

	@Override
	public void onEnable() {
		MySQL.connect();
		MySQL.registerTables();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new FreeSigns(), this);
		pm.registerEvents(new Cases(), this);
		pm.registerEvents(new PlayerJoinListener(), this);
		getCommand("addrankticket").setExecutor(new addticket());
		getCommand("addrankticket").setPermission("skypvp.manager");
	}
}
