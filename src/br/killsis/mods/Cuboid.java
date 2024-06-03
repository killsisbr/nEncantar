package br.killsis.mods;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class Cuboid {
	private Player owner;
	private String permission;
    private World world;
    private double position1X, position1Y, position1Z;

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    private double position2X, position2Y, position2Z;

    private double minX, minY, minZ, maxX, maxY, maxZ;

    public Cuboid(Location location1, Location location2) {
    	this.owner = null;
    	this.permission = "pavillion.b";
        this.position1X = location1.getX();
        this.position1Y = location1.getY();
        this.position1Z = location1.getZ();

        this.position2X = location2.getX();
        this.position2Y = location2.getY();
        this.position2Z = location2.getZ();

        setMinX(Math.min(position1X, position2X));
        setMinY(Math.min(position1Y, position2Y));
        setMinZ(Math.min(position1Z, position2Z));

        setMaxX(Math.max(position1X, position2X));
        setMaxY(Math.max(position1Y, position2Y));
        setMaxZ(Math.max(position1Z, position2Z));


        this.world = location1.getWorld();

        if (!(isInside(location1) || isInside(location2))) {
            throw new RuntimeException("Os valores lidos não estão dentro do cuboid");
        }
    }
    
    public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Cuboid(Location location1, Location location2, String permission) {
		this.permission = null;
        this.position1X = location1.getX();
        this.position1Y = location1.getY();
        this.position1Z = location1.getZ();

        this.position2X = location2.getX();
        this.position2Y = location2.getY();
        this.position2Z = location2.getZ();

        setMinX(Math.min(position1X, position2X));
        setMinY(Math.min(position1Y, position2Y));
        setMinZ(Math.min(position1Z, position2Z));

        setMaxX(Math.max(position1X, position2X));
        setMaxY(Math.max(position1Y, position2Y));
        setMaxZ(Math.max(position1Z, position2Z));


        this.world = location1.getWorld();

        if (!(isInside(location1) || isInside(location2))) {
            throw new RuntimeException("Os valores lidos não estão dentro do cuboid");
        }
    }

    public Location getCenter(World world) {
        double centerX = minX + maxX / 2;
        double centerY = minY + maxY / 2;
        double centerZ = minZ + maxZ / 2;
        return new Location(world, centerX, centerY, centerZ);
    }

    public boolean isInside(Location location) {
        int locationX = location.getBlockX();
        int locationY = location.getBlockY();
        int locationZ = location.getBlockZ();

        return isInside(locationX, locationY, locationZ);
    }

    public boolean isInside(double x, double y, double z) {
        return (x >= getMinX() && x <= getMaxX()) && (y >= getMinY() && y <= getMaxY()) && (z >= getMinZ() && z <= getMaxZ());
    }

    public Set<Chunk> getChunks(World world) {
        Set<Chunk> chunks = new HashSet<>();

        int x1 = (int) position1X & ~0xf;
        int x2 = (int) position2X & ~0xf;
        int z1 = (int) position1Z & ~0xf;
        int z2 = (int) position2Z & ~0xf;

        for (int x = x1; x <= x2; x += 16) {
            for (int z = z1; z <= z2; z += 16) {
                chunks.add(world.getChunkAt(x >> 4, z >> 4));
            }
        }

        return chunks;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public void setMinZ(double minZ) {
        this.minZ = minZ;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public void setMaxZ(double maxZ) {
        this.maxZ = maxZ;
    }

    public double getPosition1X() {
        return position1X;
    }

    public void setPosition1X(double position1X) {
        this.position1X = position1X;
    }

    public double getPosition1Y() {
        return position1Y;
    }

    public void setPosition1Y(double position1Y) {
        this.position1Y = position1Y;
    }

    public double getPosition1Z() {
        return position1Z;
    }

    public void setPosition1Z(double position1Z) {
        this.position1Z = position1Z;
    }

    public double getPosition2X() {
        return position2X;
    }

    public void setPosition2X(double position2X) {
        this.position2X = position2X;
    }

    public double getPosition2Y() {
        return position2Y;
    }

    public void setPosition2Y(double position2Y) {
        this.position2Y = position2Y;
    }

    public double getPosition2Z() {
        return position2Z;
    }

    public void setPosition2Z(double position2Z) {
        this.position2Z = position2Z;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMinZ() {
        return minZ;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMaxZ() {
        return maxZ;
    }
    
    public boolean hasOwner() {
    	return owner != null;
    }
    
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "Cuboid [owner=" + owner + ", minX=" + minX + ", minY=" + minY + ", minZ=" + minZ + ", maxX=" + maxX
				+ ", maxY=" + maxY + ", maxZ=" + maxZ + "]";
	}
}