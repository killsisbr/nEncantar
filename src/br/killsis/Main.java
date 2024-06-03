package br.killsis;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import br.killsis.mods.Cuboid;
import br.killsis.mods.CuboidManager;
import br.killsis.mods.Encantamentos;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.WeatherChangeEvent;


public class Main extends JavaPlugin implements Listener {
//	 private static CuboidManager cuboidManager;
	 private static Main instance;
	 public static ArrayList<Player> a = new ArrayList<>();
	 public static ArrayList<Player> b = new ArrayList<>();
	 public static ArrayList<Player> c = new ArrayList<>();
	 public static ArrayList<Player> guard = new ArrayList<>();
	 public static ArrayList<Player> inFight = new ArrayList<>();
	 public static ArrayList<Player> inBlackList = new ArrayList<>();
	 public boolean combat = false;
	 public static String permissionC = "classe.c"; 
	 public static String permissionB = "classe.b"; 
	 public static String permissionA = "classe.a"; 
	 public static String permissionG = "classe.g"; 
	 private Economy economy;

	public void onEnable() {
	  
		Bukkit.getConsoleSender();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
//		cuboidManager = new CuboidManager();
		Bukkit.getServer().getPluginManager().registerEvents(new Encantamentos(this, null), this);
//		pavillionA();
//		pavillionB();
//		pavillionC();
		instance = this;
		if (!setupEconomy()) {
            getLogger().severe("Vault não encontrado. Certifique-se de ter o plugin Vault instalado.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

	}

 


	
//	@EventHandler
//	public void event(EntityDamageEvent e) {
//		if (e.getEntity() instanceof Player) {
//			Player player = (Player) e.getEntity();
//			player.sendMessage("armadura "+e.getDamage(DamageModifier.ARMOR));
//			player.sendMessage("base "+e.getDamage(DamageModifier.BASE));
//			//player.sendMessage("absorvendo "+e.getDamage(DamageModifier.ABSORPTION));
//			//player.sendMessage("resistido "+e.getDamage(DamageModifier.RESISTANCE));
//			//player.sendMessage("magico "+e.getDamage(DamageModifier.MAGIC));
//			//player.sendMessage("       --      ");
//		}
//	}
	
    @EventHandler
    public void removerChuva(WeatherChangeEvent e) {
        // verificar se vai chover
        if (e.toWeatherState()) {

            // canvela a chuva
            e.setCancelled(true);
        }
    }
//	@EventHandler
//	public void event(PlayerItemDamageEvent e) {
//		Player player = e.getPlayer();
////		ItemStack item = e.getItem();
//		double dano = e.getDamage() * 2;
//		player.damage(dano);
//		player.sendMessage("dano default" + e.getDamage());
//		player.sendMessage("dano atual:" + dano);
//	}
	
	//remover a fome e exaustao
    @EventHandler
    public void removerFome(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            e.setFoodLevel(20);
            p.setSaturation(20);
            p.setExhaustion(0);
        }
    }
	
	public static Main getInstance() {
		return instance;
	}

//	public void pavillionA() {
//		String id = "pavillion.a";
//		Location location1 = new Location(Bukkit.getWorld("Killsis"), -716, 50, 1334);
//		Location location2 = new Location(Bukkit.getWorld("Killsis"), -748, 94, 1252);
//		getCuboidManager().createCuboid(location1, location2, id);
//	}
//	public void pavillionB() {
//		String id = "pavillion.b";
//		Location location1 = new Location(Bukkit.getWorld("Killsis"), -748, 50, 1383);
//		Location location2 = new Location(Bukkit.getWorld("Killsis"), -728, 94, 1442);
//		getCuboidManager().createCuboid(location1, location2, id);
//	}
//	public void pavillionC() {
//		String id = "pavillion.c";
//		Location location1 = new Location(Bukkit.getWorld("Killsis"), -711, 50, 1375);
//		Location location2 = new Location(Bukkit.getWorld("Killsis"), -654, 94, 1349);
//		getCuboidManager().createCuboid(location1, location2, id);
//	}
	
	private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            economy = rsp.getProvider();
        }
        return economy != null;
    }
//	public static CuboidManager getCuboidManager() {
//		return cuboidManager;
//	}
//
//	public void setCuboidmanager(CuboidManager cuboidManager) {
//		Main.cuboidManager = cuboidManager;
//	}
//	
//	public boolean inCombat(Player p) {
//		if(inFight.contains(p)) {
//			
//		}else {
//		p.sendMessage("Você entrou em combate");
//		inFight.add(p);
//		new BukkitRunnable() {
//
//			public void run() {
//
//				p.sendMessage("você saiu do combate.");
//				inFight.remove(p);
//			}
//		}.runTaskLater(Main.getInstance(), 20 * 10);
//
//	}
//		return true;	
//
//}
//	
//	
//	
//
//
//	public boolean BlackList(Player p) {
//		if(inBlackList.contains(p)) {
//			
//		}else {
//		p.sendMessage("Você comecou uma confusão nos pavilhoes.");
//		p.sendMessage("ficara com seu pvp ativo nas celas por 1 hora");
//		inBlackList.add(p);
//		new BukkitRunnable() {
//
//			public void run() {
//
//				p.sendMessage("Seu pvp foi redefinido para neutro.");
//				inBlackList.remove(p);
//			}
//		}.runTaskLater(Main.getInstance(), 20 * 1000);
//
//	}
//		return true;
//
//}
//
//
//		
//	
//	
//    @EventHandler
//    public void onPlayerDeath(PlayerDeathEvent event) {
//  	  Player entity = (Player) event.getEntity(); 
//  	  Player matador = (Player) event.getEntity().getKiller();
//
//  	  for (Cuboid cuboid : getCuboidManager().getCuboids()) {
//  		  if (cuboid.isInside(entity.getLocation())) {
//	
//	        String playerName = event.getEntity().getName();
//	        String deathMessage = ChatColor.RED + playerName + " morreu em um pavilhão!";
//	        if(!inBlackList.contains(entity)) {
//		        event.setKeepInventory(true);
//		        event.setKeepLevel(true);
//		        BlackList(matador);
//	        }
//	        // Envie a mensagem para todos os jogadores online.
//	        getServer().broadcastMessage(deathMessage);
//	        
//	        //caso o cara que morreu, não tava na blacklist, ele adiciona o assasino na blacklist.
//  		  }else {
//  	        event.setKeepInventory(false);
//  	        event.setKeepLevel(false);
//  		  }
//  	  }
//    }
//
//	  @EventHandler 
//	  public void onDamagEvent(EntityDamageByEntityEvent event) {
//	  
//		  if (!(event.getEntity() instanceof 
//			  Player && event.getDamager() instanceof
//			  Player)) { return; }
//	  
//	  
//	  Player entity = (Player) event.getEntity(); Player damager = (Player)
//	  event.getDamager();
//	  
//	    for (Cuboid cuboid : getCuboidManager().getCuboids()) {
//	        if (cuboid.isInside(entity.getLocation()) && cuboid.isInside(damager.getLocation())) {
//	
//	        	
//	        	boolean isDamagerA = a.contains(damager);
//	        	boolean isEntityA = a.contains(entity);
//	        	boolean isDamagerB = b.contains(damager);
//	        	boolean isEntityB = b.contains(entity);
//	        	boolean isDamagerC = c.contains(damager);
//	        	boolean isEntityC = c.contains(entity);
//	        	boolean isEntityGuard = guard.contains(entity);
//	        	boolean isDamagerGuard = guard.contains(damager);
//	        	boolean PlayerInCombat = inFight.contains(entity);
//	        	
//	        	
//	        	
//	        	
//	        	//player C bater no B cancela ou se for B em combate, ou se for A em combate.
//	        	//se o B bater no C, dentro da area, adiciona no inCombat.
//	        	//Se A batendo no C dentro da area, adiciona no inCombat.
//	        	//Se for B batendo no A ele cancel.
//	        	//Se o Damager nao for igual ao proprio pav, ou for em combat
//	        	
//	        	if ((isDamagerA && isEntityA) || (isDamagerB && isEntityB) || (isDamagerC && isEntityC) ) {
//	        	    damager.sendMessage("Voce não pode agredir companheiros de pavilhão.");
//	        		event.setCancelled(true);
//	        		return;
//	        	}
//				if (isEntityA && !PlayerInCombat && !isDamagerGuard){
//					damager.sendMessage("Esse detento nao quer briga.");
//					event.setCancelled(true);
//					return;
//				}
//				//se o cara que apanhou é um guarda, e não esta em combate, e o agressor nao é guarda tbm, cancela o evento
//				if (isEntityGuard && !PlayerInCombat && !isDamagerGuard){
//					damager.sendMessage("Você não pode agredir um guarda!.");
//					event.setCancelled(true);
//					return;
//				}
//				// se o batedor for C e saco de batata for B, e os dois nao tiverem em combate, nao ativa o pvp
//				if (isDamagerC && isEntityB && !PlayerInCombat){
//					damager.sendMessage("Esse detento nao quer briga.");
//					event.setCancelled(true);
//					return;
//				}
//				//batedor pode ser C B A e se o player tiver em combate, ativa o pvp.
//				//ideia é, caso um player de rank maior inicie uma briga, os outros ranks podem bater nesse mesmo ele sendo rank maior.
//				if ((isDamagerC || isDamagerB || isDamagerA || isDamagerGuard) && PlayerInCombat){
//					this.inCombat(entity);
//					return;
//				}
//
//				
//	        		if (!inFight.contains(damager) ) {
//		        		this.inCombat(damager);
//		        		//damager.sendMessage("Voce comecou uma briga com " + entity.getName());
//	        			return;
//	        		} //se o cara que apanhou nao esta em combate ele adiciona o cara no sistema de combate
//	        		if (!inFight.contains(entity)) {
//		        		this.inCombat(entity);
//		        		//entity.sendMessage(ChatColor.RED + "O " + ChatColor.RED+ damager.getName() + ", quer te matar!");
//	        			return;
//	        		}
//	        	    return;
//	        	}
//	        }
//	    
//	  }
	        		

//	@EventHandler
//	public void onPlayerJoin(PlayerJoinEvent event) {
//		Player player = event.getPlayer();
//	
//		player.setMaxHealth(20 + 10);
//		player.setFoodLevel(20);
//    
//		//ta adicionando prioridade em classe. PLAYER A bate em menos e menores não bate em maiores.
//		if (event.getPlayer().hasPermission(permissionG)) {
//			Main.a.remove(event.getPlayer());
//			Main.b.remove(event.getPlayer());
//			Main.c.remove(event.getPlayer());
//			Main.guard.add(event.getPlayer());
//			event.getPlayer().sendMessage(ChatColor.YELLOW + "Seu pavilhao é" + ChatColor.GREEN +" GUARDA");
//		}
//		else if (event.getPlayer().hasPermission(permissionA)) {
//			Main.guard.remove(event.getPlayer());
//			Main.b.remove(event.getPlayer());
//			Main.c.remove(event.getPlayer());
//			Main.a.add(event.getPlayer());
//			event.getPlayer().sendMessage(ChatColor.YELLOW + "Seu pavilhao é" + ChatColor.GREEN +" A");
//		}
//		else if (event.getPlayer().hasPermission(permissionB)) {
//			Main.a.remove(event.getPlayer());
//			Main.guard.remove(event.getPlayer());
//			Main.c.remove(event.getPlayer());
//			Main.b.add(event.getPlayer());
//			event.getPlayer().sendMessage(ChatColor.YELLOW + "Seu pavilhao é" + ChatColor.GREEN +" B");
//		}else if (event.getPlayer().hasPermission(permissionC)) {
//			Main.a.remove(event.getPlayer());
//			Main.b.remove(event.getPlayer());
//			Main.guard.remove(event.getPlayer());
//			Main.c.add(event.getPlayer());
//			event.getPlayer().sendMessage(ChatColor.YELLOW + "Seu pavilhao é" + ChatColor.GREEN +" C");
//			}
//			
//		
//		return;
//		
//
//		/*
//		 * Location playerLocation = event.getPlayer().getLocation();
//		 * 
//		 * Location location1 = new Location(event.getPlayer().getWorld(),
//		 * playerLocation.getX() + 5, playerLocation.getY() + 5, playerLocation.getZ() +I 
//		 * 5); Location location2 = new Location(event.getPlayer().getWorld(),
//		 * playerLocation.getX() - 5, playerLocation.getY() - 5, playerLocation.getZ() -
//		 * 5);
//		 * 
//		 * 
//		 * 
//		 * Cuboid newCuboid = getCuboidManager().createCuboid(location1, location2,
//		 * event.getPlayer());
//		 * 
//		 * getCuboidManager().getPlayerCuboids().put(event.getPlayer(), newCuboid);
//		 */
//	}

 
}