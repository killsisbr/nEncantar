package br.killsis.mods;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CuboidManager {

    private Set<Cuboid> cuboids = new HashSet<>();
	private java.util.Map<Player,Cuboid> playerCuboids = new HashMap<>();
	
	public void add(Cuboid cuboid) {
		cuboids.add(cuboid);
	}
	
	public void remove(Cuboid cuboid) {
		cuboids.remove(cuboid);
	}
	
	public Set<Cuboid> getCuboids() {
		return cuboids;
	}
	
	public void getCuboid(Cuboid cuboid) {
		cuboids.stream().filter(cuboids -> cuboids.equals(cuboid)).findFirst().orElse(null);
	}

	public java.util.Map<Player, Cuboid> getPlayerCuboids() {
		return playerCuboids;
	}

	public void setPlayerCuboids(java.util.Map<Player, Cuboid> playerCuboids) {
		this.playerCuboids = playerCuboids;
	}

	public void setCuboids(Set<Cuboid> cuboids) {
		this.cuboids = cuboids;
	}
	
	public Cuboid createCuboid(Location location1, Location location2) {
		Cuboid cuboid1 =  new Cuboid(location1, location2);
		this.add(cuboid1);
		return cuboid1;
	}
	
	public Cuboid createCuboid(Location location1, Location location2, String Permission) {
		Cuboid cuboid1 =  new Cuboid(location1, location2, Permission);
		this.add(cuboid1);
		return cuboid1;
	}
}