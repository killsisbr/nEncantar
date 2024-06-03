package br.killsis.mods;

import java.util.Random;
import java.util.Map.Entry;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

import br.killsis.Main;

import java.util.HashMap;
import java.util.Map;

import ru.tehkode.permissions.bukkit.PermissionsEx;


public class Encantamentos implements Listener {
    Map<String, Integer> permissionValues = new HashMap<>();
    List<Material> itensDesejados = new ArrayList<>();
	public Encantamentos(Main plugin, PermissionsEx pex) {
		super();
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void pot_xp (ExpBottleEvent e) {
		e.setExperience(100); //xp da pot ao quebrar
	}


    @EventHandler
    public void onPrepareItemEnchant(PrepareItemEnchantEvent event) {
        // Acesso às opções de encantamento disponíveis
    	
        int[] enchantmentLevels = event.getExpLevelCostsOffered();
        

        // Modificar as opções de encantamento para um custo de 50 níveis de experiência, por exemplo
        for (int i = 0; i < enchantmentLevels.length; i++) {
            if (enchantmentLevels[i] > 30) {
                // Aumenta o valor original em 20 (ou qualquer valor desejado)
                enchantmentLevels[i] += 20;
            }
        }
    }
	
	
	@EventHandler
	public void onEnchantItem(EnchantItemEvent e) {

        itensDesejados.add(Material.DIAMOND_PICKAXE);
        itensDesejados.add(Material.GOLD_PICKAXE);
        itensDesejados.add(Material.IRON_PICKAXE);
        itensDesejados.add(Material.STONE_PICKAXE);
        itensDesejados.add(Material.WOOD_SWORD);
        itensDesejados.add(Material.DIAMOND_AXE);
        itensDesejados.add(Material.GOLD_AXE);
        itensDesejados.add(Material.IRON_AXE);
        itensDesejados.add(Material.STONE_AXE);
        itensDesejados.add(Material.WOOD_AXE);
        itensDesejados.add(Material.DIAMOND_SPADE);
        itensDesejados.add(Material.GOLD_SPADE);
        itensDesejados.add(Material.IRON_SPADE);
        itensDesejados.add(Material.WOOD_HOE);
        itensDesejados.add(Material.IRON_HOE);
        itensDesejados.add(Material.GOLD_HOE);
        itensDesejados.add(Material.DIAMOND_HOE);
        

        // Agora você pode usar a lista para verificar se um item está na lista
		if(e.getEnchantBlock().getType().equals(Material.ENCHANTMENT_TABLE)){
		   this.xp(e);		        	
		}
		}

	@EventHandler
	public void xp(EnchantItemEvent e) {	
		int xp = e.getExpLevelCost() * 4;
		
		Player player = e.getEnchanter();
		
		if (hasPermission(player, "classe.c")){
			//gerar numero random, inteiro, (limite: xP+80), seta o limite minimo de 50.
			int userC = new Random().nextInt(xp+ 80)+ 50; //limite 150
			this.enchant(userC, e);
		}
		else if (hasPermission(player, "classe.b")){
			int userB = new Random().nextInt(xp+ 120)+ 80; //limite 240
			this.enchant(userB, e);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		}
		else if (hasPermission(player, "classe.a")){
			int userA = new Random().nextInt(xp+ 150)+ 100; //270
			this.enchant(userA, e);
		}
		else if (hasPermission(player, "classe.g")) {
			int vipBoost = new Random().nextInt(xp + 220) + 150;
			this.enchant(vipBoost, e);
		}else if (hasPermission(player, "classe.yt")) {
			int ytBoost = new Random().nextInt(xp + 180) + 120;
			this.enchant(ytBoost, e);
		}else {
			player.sendMessage("você não tem permissão para o uso.");
			e.isCancelled();
		}	
	}
	public void setLore(int nivel,EnchantItemEvent e ) {
		ItemStack item = e.getItem();
		ItemMeta meta = item.getItemMeta();
		String oneStar = "✰";
		String twoStar = "✰✰";
		String treeStar = "✰✰✰";
		String forStar = "✰✰✰✰";
		String fiveStar = "✰✰✰✰✰";
		int danoTotal = nivel;;
		List<String> lore = new ArrayList<>();
		lore.add("");
		// Adicione a linha de raridade com base no valor de danoTotal
		if (danoTotal <= 170) {
			lore.add("§4§lR§FARIDADE: §4"  + oneStar); // dano seja menor que 120
		} else if (danoTotal <= 210 && danoTotal >=171 ) {
			lore.add("§4§lR§FARIDADE: §4"  + twoStar); // Primeira linha
		} else if (danoTotal <= 270 && danoTotal >= 221) {
			lore.add("§4§lR§FARIDADE: §4" + treeStar); // Primeira linha
		} else if (danoTotal <= 319 && danoTotal >=271) {
			lore.add("§4§lR§FARIDADE: §4" + forStar);
			e.getEnchanter().playSound(e.getEnchanter().getLocation(), Sound.LEVEL_UP, 5.0F, 5.0F);// Primeira linha
		}  else if (danoTotal <= 350 && danoTotal >= 320) {
			e.getEnchanter().playSound(e.getEnchanter().getLocation(), Sound.LEVEL_UP, 5.0F, 5.0F);
			lore.add("§4§l2R§FARIDADE: §4" + fiveStar); // Primeira linha
		}
		lore.add("§6§lE§fncantado §6p§for §4§l" + e.getEnchanter().getDisplayName());
		
		// Se você desejar adicionar mais informações à lore, basta adicionar linhas adicionais aqui
		
		// Obtenha a ItemMeta do item
		
		// Verifique se o ItemMeta não é nulo
		if (meta != null) {
			// Adicione as linhas de lore ao ItemMeta
			meta.setLore(lore);
			
			// Defina o ItemMeta atualizado no item
			item.setItemMeta(meta);
		}
	}
    
	   
    @EventHandler
    public void onAnvilUse(InventoryClickEvent event, EnchantItemEvent e) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        
        Player player = (Player) event.getWhoClicked();
        if (!(event.getInventory() instanceof AnvilInventory)) return;
        
        AnvilInventory anvilInventory = (AnvilInventory) event.getInventory();
        
        ItemStack[] contents = anvilInventory.getContents();
        if (contents.length < 2) return;
        
        ItemStack leftItem = contents[0];
        ItemStack rightItem = contents[1];
        
        if (leftItem != null && rightItem != null && rightItem.getType() == Material.ENCHANTED_BOOK) {
            ItemStack result = anvilInventory.getItem(2);
            
            // Verifica se o item à esquerda é uma espada de diamante
            if (result != null && result.getType() == Material.DIAMOND_SWORD) {
                // Obtém os encantamentos do livro
                for (Enchantment ench : rightItem.getEnchantments().keySet()) {
                    // Adiciona os encantamentos do livro ao item resultante
                    result.addUnsafeEnchantment(ench, rightItem.getEnchantmentLevel(ench));
                    e.getEnchantsToAdd().put(ench, rightItem.getEnchantmentLevel(ench));
                }
                player.sendMessage("Você encantou sua espada usando o livro na bigorna!");
            }
        }
    }

	public void enchant(int nivel, EnchantItemEvent e) {
		  for (Entry<Enchantment, Integer> a : e.getEnchantsToAdd().entrySet()) {
			  
			    int xp = e.getExpLevelCost();
				int ToolsBoost = new Random().nextInt(xp) ;
				  
			  
			    if(a.getKey().equals(Enchantment.PROTECTION_ENVIRONMENTAL)) {
				   e.getEnchantsToAdd().put(Enchantment.PROTECTION_ENVIRONMENTAL,nivel);
				   this.setLore(nivel, e);
			    }
			    if(a.getKey().equals(Enchantment.PROTECTION_EXPLOSIONS)) {
					e.getEnchantsToAdd().put(Enchantment.PROTECTION_EXPLOSIONS, nivel);
				}
			    if(a.getKey().equals(Enchantment.PROTECTION_FIRE)) {
					e.getEnchantsToAdd().put(Enchantment.PROTECTION_FIRE, nivel);
				}
			    if(a.getKey().equals(Enchantment.PROTECTION_PROJECTILE)) {
					e.getEnchantsToAdd().put(Enchantment.PROTECTION_PROJECTILE, nivel);
				}
			    if(a.getKey().equals(Enchantment.DURABILITY)) {
			     	if(e.getItem().getType() == Material.DIAMOND_PICKAXE) {
			    		e.getEnchantsToAdd().put(Enchantment.DURABILITY, ToolsBoost);
			    	}else if(e.getItem().getType() == Material.IRON_PICKAXE) {
			    		e.getEnchantsToAdd().put(Enchantment.DURABILITY, ToolsBoost);
			    	}else if(e.getItem().getType() == Material.GOLD_PICKAXE) {
			    		e.getEnchantsToAdd().put(Enchantment.DURABILITY, ToolsBoost);
			    	}else if(e.getItem().getType() == Material.WOOD_PICKAXE) {
			    			e.getEnchantsToAdd().put(Enchantment.DURABILITY, ToolsBoost);
			    	}else if(e.getItem().getType() == Material.STONE_PICKAXE) {
		    			e.getEnchantsToAdd().put(Enchantment.DURABILITY, ToolsBoost);
		    	}
			    	else {
			    		e.getEnchantsToAdd().put(Enchantment.DURABILITY, nivel);
			    	}
				}
			    if(a.getKey().equals(Enchantment.ARROW_DAMAGE)) {
					e.getEnchantsToAdd().put(Enchantment.ARROW_DAMAGE, nivel);
				}
			    if(a.getKey().equals(Enchantment.WATER_WORKER)) {
					e.getEnchantsToAdd().put(Enchantment.ARROW_DAMAGE, nivel);
				}
			    if(a.getKey().equals(Enchantment.THORNS)) {
					e.getEnchantsToAdd().put(Enchantment.THORNS, 1);
				}
			    if(a.getKey().equals(Enchantment.OXYGEN)) {
					e.getEnchantsToAdd().put(Enchantment.OXYGEN, nivel);
				}
			    //ferramentas e armas
			    if(a.getKey().equals(Enchantment.FIRE_ASPECT)) {
					e.getEnchantsToAdd().put(Enchantment.FIRE_ASPECT, nivel);
				}
			    if(a.getKey().equals(Enchantment.DIG_SPEED)) {
			    	if(e.getItem().getType() == Material.DIAMOND_PICKAXE) {
			    		e.getEnchantsToAdd().put(Enchantment.DIG_SPEED, ToolsBoost);
			    	}else if(e.getItem().getType() == Material.IRON_PICKAXE) {
			    		e.getEnchantsToAdd().put(Enchantment.DIG_SPEED, ToolsBoost);
			    	}else if(e.getItem().getType() == Material.GOLD_PICKAXE) {
			    		e.getEnchantsToAdd().put(Enchantment.DIG_SPEED, ToolsBoost);
			    	}
			    	else if(e.getItem().getType() == Material.WOOD_PICKAXE) {
			    		e.getEnchantsToAdd().put(Enchantment.DIG_SPEED, ToolsBoost);
			    	}
			    	else if(e.getItem().getType() == Material.STONE_PICKAXE) {
			    		e.getEnchantsToAdd().put(Enchantment.DIG_SPEED, ToolsBoost);
			    	}else {
			    		e.getEnchantsToAdd().put(Enchantment.DIG_SPEED, nivel);
			    	}
				}
			    if(a.getKey().equals(Enchantment.FIRE_ASPECT)) {
					e.getEnchantsToAdd().put(Enchantment.FIRE_ASPECT, 1);
				}
			    if(a.getKey().equals(Enchantment.DAMAGE_ALL)) {
					e.getEnchantsToAdd().put(Enchantment.DAMAGE_ALL, nivel);
					 this.setLore(nivel, e);
				}
			    if(a.getKey().equals(Enchantment.DAMAGE_ARTHROPODS)) {
					e.getEnchantsToAdd().put(Enchantment.DAMAGE_ARTHROPODS, nivel);
				}
			    if(a.getKey().equals(Enchantment.DAMAGE_UNDEAD)) {
					e.getEnchantsToAdd().put(Enchantment.DAMAGE_UNDEAD, nivel);
				}
			    if(a.getKey().equals(Enchantment.ARROW_DAMAGE)) {
					e.getEnchantsToAdd().put(Enchantment.ARROW_DAMAGE, nivel);
					 this.setLore(nivel, e);
				}
			    if(a.getKey().equals(Enchantment.ARROW_INFINITE)) {
					e.getEnchantsToAdd().put(Enchantment.ARROW_INFINITE, nivel);
				}
			    if(a.getKey().equals(Enchantment.DEPTH_STRIDER)) {
					e.getEnchantsToAdd().put(Enchantment.DEPTH_STRIDER, nivel);
				}
			    if(a.getKey().equals(Enchantment.LOOT_BONUS_BLOCKS)) {
			    	if(e.getItem().getType() == Material.DIAMOND_PICKAXE) {
			    		e.getEnchantsToAdd().put(Enchantment.LOOT_BONUS_BLOCKS, ToolsBoost);
			    	}else if(e.getItem().getType() == Material.STONE_PICKAXE) {
			    		e.getEnchantsToAdd().put(Enchantment.DIG_SPEED, ToolsBoost);
			    	}else if(e.getItem().getType() == Material.WOOD_PICKAXE) {
			    		e.getEnchantsToAdd().put(Enchantment.DIG_SPEED, ToolsBoost);
			    	}
			    	else if(e.getItem().getType() == Material.IRON_PICKAXE) {
			    		e.getEnchantsToAdd().put(Enchantment.LOOT_BONUS_BLOCKS, ToolsBoost);
			    	}else if(e.getItem().getType() == Material.GOLD_PICKAXE) {
			    		e.getEnchantsToAdd().put(Enchantment.LOOT_BONUS_BLOCKS, ToolsBoost);
			    	}else {
			    		e.getEnchantsToAdd().put(Enchantment.LOOT_BONUS_BLOCKS, nivel);
			    	}
				}
			    if(a.getKey().equals(Enchantment.LOOT_BONUS_MOBS)) {
			    	if(e.getItem().getType() == Material.DIAMOND_SWORD) {
			    		e.getEnchantsToAdd().put(Enchantment.LOOT_BONUS_MOBS, ToolsBoost);
			    	}else if(e.getItem().getType() == Material.IRON_SWORD) {
			    		e.getEnchantsToAdd().put(Enchantment.LOOT_BONUS_MOBS, ToolsBoost);}
			    	else if(e.getItem().getType() == Material.WOOD_SWORD) {
			    		e.getEnchantsToAdd().put(Enchantment.LOOT_BONUS_MOBS, ToolsBoost);}
			    	else if(e.getItem().getType() == Material.STONE_SWORD) {
			    		e.getEnchantsToAdd().put(Enchantment.LOOT_BONUS_MOBS, ToolsBoost);
			    	}else if(e.getItem().getType() == Material.GOLD_SWORD) {
			    		e.getEnchantsToAdd().put(Enchantment.LOOT_BONUS_MOBS, ToolsBoost);
			    	}else {
			    		e.getEnchantsToAdd().put(Enchantment.LOOT_BONUS_MOBS, nivel);
			    	}
				}
			    if(a.getKey().equals(Enchantment.LUCK)) {
			    	
					e.getEnchantsToAdd().put(Enchantment.LUCK, new Random().nextInt(nivel) );
				}
			    if(a.getKey().equals(Enchantment.LURE)) {
					e.getEnchantsToAdd().put(Enchantment.LURE, new Random().nextInt(nivel) );
				}
			   
		    
	 }

		if(e.getItem().getType() == Material.BOOK) {
			this.setLore(nivel, e);
		}
		
	 
}
	  
	private boolean hasPermission(Player player, String permission) {
		    return PermissionsEx.getPermissionManager().has(player, permission);
		}
	
}
