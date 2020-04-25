package me.ltxom.scoreboardshop.gui;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShopInventory implements Listener {
	private Map<String, Inventory> guiMap;
	private Map<Player, Map<ItemStack, Inventory>> categoryGuiMap; // <Player - <Category Name - Inventory>>
	private FileConfiguration languageConfig;
	private FileConfiguration shopConfig;
	private Server server;

	public ShopInventory(FileConfiguration languageConfig, FileConfiguration shopConfig, Server server) {
		guiMap = new HashMap<>();
		categoryGuiMap = new HashMap<>();
		this.languageConfig = languageConfig;
		this.shopConfig = shopConfig;
		this.server = server;
	}

	public void openInventory(Player player) {
		if (!guiMap.containsKey(player.getUniqueId().toString())) {
			loadGui(player);
		}
		player.openInventory(guiMap.get(player.getUniqueId().toString()));
	}

	public void loadGui(Player player) {
		categoryGuiMap.put(player, new HashMap<>());
		Inventory gui = Bukkit.createInventory(player, 54, ChatColor.AQUA + "Scoreboard Shop");

		// close
		setClose(gui);

		// category
		int counter = 0;
		Set<String> keys = shopConfig.getKeys(false);
		for (String key : keys) {
			ItemStack itemStack = setCategory(key);
			categoryGuiMap.get(player).put(itemStack, loadItemGui(player,
					shopConfig.get(key + ".display_name").toString()));
			gui.setItem(counter++, itemStack);
		}

		guiMap.put(player.getUniqueId().toString(), gui);
	}

	public Inventory loadItemGui(Player player, String categoryDisplayName) {
		Inventory itemGui = Bukkit.createInventory(player, 54, categoryDisplayName);
		// close
		setClose(itemGui);

		return itemGui;
	}

	private void setClose(Inventory itemGui) {
		ItemStack close = new ItemStack(Material.BARRIER);
		ItemMeta closeMeta = close.getItemMeta();
		closeMeta.setDisplayName(languageConfig.get("close").toString());
		close.setItemMeta(closeMeta);

		itemGui.setItem(53, close);
	}

	public void reloadGui() {
		for (String k : guiMap.keySet()) {
			// category
			int counter = 0;
			Set<String> keys = shopConfig.getKeys(false);
			for (String key : keys) {
				guiMap.get(k).setItem(counter++, setCategory(key));
			}
		}
	}

	private ItemStack setCategory(String key) {
		String displayName = shopConfig.get(key + ".display_name").toString();
		Material displayMaterial = Material.getMaterial(shopConfig.get(key + ".display_item").toString());
		ItemStack category = new ItemStack(displayMaterial);
		ItemMeta itemMeta = category.getItemMeta();
		itemMeta.setDisplayName(displayName);
		category.setItemMeta(itemMeta);
		return category;
	}

	@EventHandler
	public void clickEvent(InventoryClickEvent e) {
		Inventory gui = guiMap.get(e.getWhoClicked().getUniqueId().toString());
		try {
			// in category gui
			if (e.getView().getTopInventory().equals(gui)) {
				e.setCancelled(true);
				ItemStack itemStack = e.getCurrentItem();
				if (e.getWhoClicked() instanceof Player) {
					Player player = server.getPlayer(e.getWhoClicked().getName());
					if (itemStack.getType().equals(Material.BARRIER) && itemStack.getItemMeta().getDisplayName().equals(languageConfig.get("close").toString())) {
						// close
						e.getView().close();
					} else {
						if (categoryGuiMap.get(player).containsKey(itemStack)) {
							player.openInventory(categoryGuiMap.get(player).get(itemStack));
						}
					}
				}
				return;
			}
			if (e.getWhoClicked() instanceof Player) {
				Player player = server.getPlayer(e.getWhoClicked().getName());
				Map<ItemStack, Inventory> map = categoryGuiMap.get(player);
				boolean flag = false;
				for (Inventory inventory : map.values()) {
					if (inventory.equals(e.getView().getTopInventory())) {
						flag = true;
						break;
					}
				}
				ItemStack itemStack = e.getCurrentItem();
				if (flag) {
					e.setCancelled(true);
					if (itemStack.getType().equals(Material.BARRIER) && itemStack.getItemMeta().getDisplayName().equals(languageConfig.get("close").toString())) {
						// close
						openInventory(player);
					}
				}
			}
		} catch (NullPointerException e1) {
			// ignore
		}

	}
}