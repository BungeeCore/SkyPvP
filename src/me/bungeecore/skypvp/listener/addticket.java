package me.bungeecore.skypvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bungeecore.skypvp.sql.MySQL;

public class addticket implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		
		if (arg3.length == 2) {
			String name = arg3[0];
			String anzahl = arg3[1];
			
			new MySQL().addRankCrate(Bukkit.getPlayer(arg0.getName()).getUniqueId(), Integer.parseInt(anzahl));
			
			
		}
		return false;
	}

}
