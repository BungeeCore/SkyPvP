package me.bungeecore.skypvp.listener;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.bungeecore.skypvp.SkyPvP;
import me.bungeecore.skypvp.sql.MySQL;


public class Cases implements Listener {

	String glass = "§o";

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.TRAPPED_CHEST && p.getGameMode() != GameMode.CREATIVE) {
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, "§6Crates");
				ArrayList<String> daily = new ArrayList<>();
				ArrayList<String> rank = new ArrayList<>();
				ArrayList<String> coins = new ArrayList<>();
				daily.add("§cDerzeit nicht verfügbar!");
				coins.add("§cDerzeit nicht verfügbar!");
				//				if (new MySQL().getNormalCrates(p.getUniqueId()) == 0) {
				//					daily.add("§7Deine Crates: §a0");
				//				} else {
				//					daily.add("§7Deine Crates: §a" + new MySQL().getNormalCrates(p.getUniqueId()));
				//				}
				if (new MySQL().getRankCrates(p.getUniqueId()) == 0) {
					rank.add("§7Deine Crates: §a0");
				} else {
					rank.add("§7Deine Crates: §a" + new MySQL().getRankCrates(p.getUniqueId()));
				}
				//				if (new MySQL().getCoinsCrates(p.getUniqueId()) == 0) {
				//					coins.add("§7Deine Crates: §a0");
				//				} else {
				//					coins.add("§7Deine Crates: §a" + new MySQL().getCoinsCrates(p.getUniqueId()));
				//				}
				inv.setItem(1, createItem(Material.EMERALD, 1, 0, "§dDaily Crate", daily));
				inv.setItem(2, createItem(Material.BOOK, 1, 0, "§dRang Crate", rank));
				inv.setItem(3, createItem(Material.GOLD_INGOT, 1, 0, "§6Coins Crate", coins));
				//inv.setItem(4, createItem(Material.CHEST, 1, 0, "§6Key kaufen", null));

				fillWithGlass(inv);

				p.openInventory(inv);
			}
		}
	}

	@EventHandler
	public void onInv(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		try {
			if (e.getClickedInventory().getTitle().equalsIgnoreCase("§6Crates")) {
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName() == "§dRang Crate") {
					if (new MySQL().getRankCrates(p.getUniqueId()) != 0) {
						new MySQL().removeRankCrate(p.getUniqueId(), 1);
						Inventory inv = Bukkit.createInventory(null, 27, "§6Rang Crate");
						startSpingRank(p, inv);
						return;
					} else {
						e.setCancelled(true);
						p.closeInventory();
						p.sendMessage("§7| §9SkyPvP §7| §cDu hast leider keine Keys!"); 
					}
				}
			}
			if (e.getClickedInventory().getTitle().equalsIgnoreCase("§6Rang Crate")) {
				e.setCancelled(true);
			}
		} catch (Exception ex) {
			// TODO: handle exception
		}
	}

	public ItemStack IDStack(final String Display, final int id, final int Anzahl, final int subid) {
		final ItemStack istack52 = new ItemStack(id, Anzahl, (short)subid);
		final ItemMeta istackMeta52 = istack52.getItemMeta();
		istackMeta52.setDisplayName(Display);
		istack52.setItemMeta(istackMeta52);
		return istack52;
	}

	public void startSpingRank(Player p, Inventory inv1) {
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName("§a");
		i.setItemMeta(m);
		ItemStack i1 = new ItemStack(Material.HOPPER, 1);
		ItemMeta m1 = i1.getItemMeta();
		m1.setDisplayName("§a");
		i1.setItemMeta(m1);

		inv1.setItem(0, i);
		inv1.setItem(1, i);
		inv1.setItem(2, i);
		inv1.setItem(3, i);
		inv1.setItem(4, i1);
		inv1.setItem(5, i);
		inv1.setItem(6, i);
		inv1.setItem(7, i);
		inv1.setItem(8, i);
		inv1.setItem(9, i);
		inv1.setItem(17, i);
		inv1.setItem(18, i);
		inv1.setItem(19, i);
		inv1.setItem(20, i);
		inv1.setItem(21, i);
		inv1.setItem(22, i);
		inv1.setItem(23, i);
		inv1.setItem(24, i);
		inv1.setItem(25, i);
		inv1.setItem(26, i);


		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 8);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 9);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 10);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 11);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 13);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 15);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 17);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 20);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 23);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 26);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 30);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 34);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 38);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 42);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 47);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 52);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 57);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 62);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 70);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 86);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 103);
		new BukkitRunnable() {

			@Override
			public void run() {
				spinRank(p, inv1);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.openInventory(inv1);
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 125);
		new BukkitRunnable() {

			@Override
			public void run() {
				String rdm = "§7| §9SkyPvP §7|";
				if (inv1.getItem(13).getItemMeta().getDisplayName().startsWith("§6Premium")) {

					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/pex user " + p.getName() + " group set Premium");
					p.kickPlayer("\n§7Rang Update\n\n§7Neuer Rang - §6Premium");
					return;
				}
				if (inv1.getItem(13).getItemMeta().getDisplayName().startsWith("§aSkyKing")) {

					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/pex user " + p.getName() + " group set SkyKing");
					p.kickPlayer("\n§7Rang Update\n\n§7Neuer Rang - §aSkyKing");
					return;
				}
				if (inv1.getItem(13).getItemMeta().getDisplayName().startsWith("§9Elite")) {

					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/pex user " + p.getName() + " group set Elite");
					p.kickPlayer("\n§7Rang Update\n\n§7Neuer Rang - §9Elite");
					return;
				}
				if (inv1.getItem(13).getItemMeta().getDisplayName().startsWith("§aSkyLord")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/pex user " + p.getName() + " group set SkyLord");
					p.kickPlayer("\n§7Rang Update\n\n§7Neuer Rang - §aSkyLord");
					return;
				}
				if (inv1.getItem(13).getItemMeta().getDisplayName().startsWith("§61 Million Coins")) {
					new MySQL().addCoins(p.getUniqueId(), 1000000);
					p.sendMessage(rdm + "§7Du hast die §61 Millionen Coins §7gewonnen!");
					p.closeInventory();
					return;
				}
				if (inv1.getItem(13).getItemMeta().getDisplayName().startsWith("§cNichts")) {
					p.closeInventory();
					p.sendMessage("§7| §9SkyPvP §7| §cDu hast leider nichts gewonnen!");
					return;
				}
			}
		}.runTaskLater(SkyPvP.getPlugin(SkyPvP.class), 140);
	}


	private void spinRank(Player p, Inventory inv) {
		Random r = new Random();
		int Low = 1;
		int High = 100;
		int R = r.nextInt(High - Low) + Low;

		ItemStack y2 = inv.getItem(16);
		ItemStack y3 = inv.getItem(15);
		ItemStack y4 = inv.getItem(14);
		ItemStack y5 = inv.getItem(13);
		ItemStack y6 = inv.getItem(12);
		ItemStack y7 = inv.getItem(11);

		inv.setItem(15, y2);
		inv.setItem(14, y3);
		inv.setItem(13, y4);
		inv.setItem(12, y5);
		inv.setItem(11, y6);
		inv.setItem(10, y7);

		Random r1 = new Random();
		int g1 = r1.nextInt(10);
		if(g1 == 1) {
			ItemStack i = new ItemStack(Material.BARRIER);
			ItemMeta m = i.getItemMeta();
			m.setDisplayName("§cNichts");
			i.setItemMeta(m);
			inv.setItem(16, i);
		} else if(g1 == 2) {
			ItemStack i = new ItemStack(Material.GOLD_BLOCK);
			ItemMeta m = i.getItemMeta();
			m.setDisplayName("§61 Million Coins");
			i.setItemMeta(m);
			inv.setItem(16, i);
		} else if(g1 == 3) {
			ItemStack i = new ItemStack(Material.GOLD_INGOT);
			ItemMeta m = i.getItemMeta();
			m.setDisplayName("§6Premium §cLifetime §7(§6§lLEGENDÄR§7)");
			i.setItemMeta(m);
			inv.setItem(16, i);
		} else if(g1 == 4) {
			ItemStack i = new ItemStack(Material.BARRIER);
			ItemMeta m = i.getItemMeta();
			m.setDisplayName("§cNichts");
			i.setItemMeta(m);
			inv.setItem(16, i);
		} else if(g1 == 5) {
			ItemStack i = new ItemStack(Material.BARRIER);
			ItemMeta m = i.getItemMeta();
			m.setDisplayName("§cNichts");
			i.setItemMeta(m);
			inv.setItem(16, i);
		} else if(g1 == 6) {
			ItemStack i = new ItemStack(Material.EMERALD);
			ItemMeta m = i.getItemMeta();
			m.setDisplayName("§aSkyKing §cLIFETIME §7(§6§lLEGENDÄR§7)");
			i.setItemMeta(m);
			inv.setItem(16, i);
		} else if(g1 == 7) {
			ItemStack i = new ItemStack(Material.BARRIER);
			ItemMeta m = i.getItemMeta();
			m.setDisplayName("§cNichts");
			i.setItemMeta(m);
			inv.setItem(16, i);
		} else if(g1 == 8) {
			ItemStack i = new ItemStack(Material.BARRIER);
			ItemMeta m = i.getItemMeta();
			m.setDisplayName("§cNichts");
			i.setItemMeta(m);
			inv.setItem(16, i);
		} else if(g1 == 9) {
			ItemStack i = new ItemStack(Material.NETHER_STAR);
			ItemMeta m = i.getItemMeta();
			m.setDisplayName("§9Elite §cLifetime §7(§6§lLEGENDÄR§7)");
			i.setItemMeta(m);
			inv.setItem(16, i);
		} else if(g1 == 10) {
			ItemStack i = new ItemStack(Material.DIAMOND);
			ItemMeta m = i.getItemMeta();
			m.setDisplayName("§aSkyLord §cLIFETIME §7(§6§lLEGENDÄR§7)");
			i.setItemMeta(m);
			inv.setItem(16, i);
		} else {
			System.err.println("ERROR");
		}

	}


	public ItemStack createItem(Material material, int amount, int subid, String name, ArrayList<String> lore) {
		ItemStack item = new ItemStack(material, amount, (short) subid);
		ItemMeta iMeta = item.getItemMeta();
		iMeta.setDisplayName(name);
		iMeta.setLore(lore);
		item.setItemMeta(iMeta);
		return item;
	}

	public void fillWithGlass(Inventory inv) {
		ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta iMeta = item.getItemMeta();
		iMeta.setDisplayName(glass);
		item.setItemMeta(iMeta);
		for (int i = 0; i < inv.getSize(); i++) {
			if (inv.getItem(i) == null) {
				inv.setItem(i, item);
			}
		}
	}
}
