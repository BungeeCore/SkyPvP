package me.bungeecore.skypvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FreeSigns implements Listener {

	String invname = "§6§lKostenlose Items";
	String glas = "§o";

	@EventHandler
	public void on(PlayerInteractEntityEvent e){
		Entity en = e.getRightClicked();
		Player p = e.getPlayer();

		if(en instanceof ItemFrame){
			ItemFrame itemframe = (ItemFrame) en;

			if(itemframe.getItem().getType() == Material.AIR || itemframe.getItem() == null){
				return;
			}
			e.setCancelled(true);
			openInventory(p, itemframe.getItem(), 1);
		}
	}

	@EventHandler
	public void on(InventoryClickEvent e){
		try{
			if(e.getInventory().getName().equalsIgnoreCase(invname)){
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(glas)){
					e.setCancelled(true);
				}
			}
		} catch (NullPointerException d){
		}
	}

	@EventHandler
	public void on(EntityDamageByEntityEvent e){
		Entity en = e.getEntity();
		Entity damager = e.getDamager();

		if(en instanceof ItemFrame){
			if(damager instanceof Player){
				Player p = (Player) e.getDamager();

				if(!(p.hasPermission("skypvp.removeframe") && p.getGameMode() == GameMode.CREATIVE)){
					e.setCancelled(true);
					return;
				}
			}
		}
	}

	private void openInventory(Player p, ItemStack item, int anzahl) {
		Inventory inv = Bukkit.createInventory(null, 9, invname);

		for(int i = 0; i != 9; i++){
			ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(glas);
			is.setItemMeta(im);
			inv.setItem(i, is);
		}

		if(item.getMaxStackSize() > 1){
			item.setAmount(anzahl);
		}

		inv.setItem(0, item);
		inv.setItem(1, item);
		inv.setItem(2, item);
		inv.setItem(3, item);
		inv.setItem(4, item);
		inv.setItem(5, item);
		inv.setItem(6, item);
		inv.setItem(7, item);
		inv.setItem(8, item);
		
		p.openInventory(inv);
	}
}
