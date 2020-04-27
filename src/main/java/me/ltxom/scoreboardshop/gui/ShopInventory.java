package me.ltxom.scoreboardshop.gui;


import me.ltxom.scoreboardshop.entity.ShopItem;
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

import java.util.*;

// 不知不觉造出来的屎山，但能用
// TODO 优化


public class ShopInventory implements Listener {
	private Map<String, Inventory> guiMap;
	private Map<Player, Map<ItemStack, Inventory>> categoryGuiMap; // <Player - <Category Name - Inventory>>
	private Map<ItemStack, ShopItem> itemStackShopItemMap;
	private FileConfiguration languageConfig;
	private FileConfiguration shopConfig;
	private Server server;

	public ShopInventory(FileConfiguration languageConfig, FileConfiguration shopConfig, Server server) {
		itemStackShopItemMap = new HashMap<>();
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
			categoryGuiMap.get(player).put(itemStack, loadItemGui(player, key,
					shopConfig.get(key + ".display_name").toString()));
			gui.setItem(counter++, itemStack);
		}

		guiMap.put(player.getUniqueId().toString(), gui);
	}

	public Inventory loadItemGui(Player player, String categoryName, String categoryDisplayName) {
		Inventory itemGui = Bukkit.createInventory(player, 54, categoryDisplayName);
		// close
		setClose(itemGui);
		Set<String> keys = shopConfig.getKeys(true);

		String nextItemIndex;
		int counter = 1;
		while (keys.contains(categoryName + ".items.item-" + counter)) {
			nextItemIndex = "item-" + counter;
			ShopItem shopItem = new ShopItem(shopConfig.getString((categoryName + ".items." + nextItemIndex +
					".shopitem")), shopConfig.getItemStack((categoryName + ".items." + nextItemIndex +
					".itemstack")));
			if (shopItem.getItemType().equals("HAND")) {
				itemGui.setItem(counter - 1, shopItem.getItemStack());
				itemStackShopItemMap.put(shopItem.getItemStack(), shopItem);
			} else {
				ItemStack commandItem = new ItemStack(Material.getMaterial(shopItem.getMaterial()));
				ItemMeta itemMeta = commandItem.getItemMeta();
				itemMeta.setDisplayName(shopItem.getDisplayName());
				List list = new ArrayList();
				for (String str : shopItem.getLore().split("__")) {
					list.add(str);
				}
				itemMeta.setLore(list);
				commandItem.setItemMeta(itemMeta);
				itemGui.setItem(counter - 1, commandItem);
				itemStackShopItemMap.put(commandItem, shopItem);
			}
			counter++;
		}

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
		HashMap<String, ItemStack> cateNameToItemMap = new HashMap<>();
		for (String k : guiMap.keySet()) {
			// recreate category inventory
			guiMap.put(k, Bukkit.createInventory(guiMap.get(k).getHolder(), 54, ChatColor.AQUA + "Scoreboard " +
					"Shop"));
			setClose(guiMap.get(k));

			// reload category
			int counter = 0;
			Set<String> keys = shopConfig.getKeys(false);
			for (String key : keys) {
				ItemStack itemStack = setCategory(key);
				guiMap.get(k).setItem(counter++, itemStack);
				cateNameToItemMap.put(key, itemStack);
			}

		}
		// recreate items inventory
		Set<String> keys = shopConfig.getKeys(false);
		for (String key : keys) {
			for (Player player : categoryGuiMap.keySet()) {
				categoryGuiMap.get(player).put(cateNameToItemMap.get(key), loadItemGui(player, key,
						shopConfig.get(key + ".display_name").toString()));
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
					} else {
						ShopItem shopItem = itemStackShopItemMap.get(itemStack);
						if (shopItem != null) {
							int score =
									player.getScoreboard().getObjective(shopItem.getScoreboardVarName()).getScore(player).getScore();
							if (score >= shopItem.getPrice() && score - shopItem.getPrice() >= 0) {
								player.getScoreboard().getObjective(shopItem.getScoreboardVarName()).getScore(player).setScore((int) (score - shopItem.getPrice()));
								if (shopItem.getItemType().equals("HAND")) {
									itemStack = e.getCurrentItem().clone();
									ItemMeta itemMeta = itemStack.getItemMeta();
									List<String> lore = itemMeta.getLore();
									lore.remove(lore.size() - 1);
									lore.remove(lore.size() - 1);

									itemMeta.setLore(lore);
									itemStack.setItemMeta(itemMeta);
									player.getInventory().addItem(itemStack);
								} else if (shopItem.getItemType().equals("COMMAND")) {
									String command = shopItem.getItemCommand();
									String target = command;
									if (command.contains("%p%")) target = command.replace("%p%", player.getName());
									if (target.startsWith("/")) target = target.substring(1);
									Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), target);
								}
							} else {
								player.sendMessage("§c" + languageConfig.get("no-enough-score").toString());
							}

						}
					}
				}
			}
		} catch (NullPointerException e1) {
			// ignore
		}

	}
}