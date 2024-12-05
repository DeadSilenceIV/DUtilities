package us.lynuxcraft.deadsilenceiv.dutilities.regions;

import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Region {
    @Setter private World world;
    private final int x1, y1, z1;
    private final int x2, y2, z2;
    public Region(Location pointA, Location pointB) {
        if (!pointA.getWorld().equals(pointB.getWorld())) throw new IllegalArgumentException("Locations must be on the same world");
        this.world = pointA.getWorld();
        this.x1 = Math.min(pointA.getBlockX(), pointB.getBlockX());
        this.y1 = Math.min(pointA.getBlockY(), pointB.getBlockY());
        this.z1 = Math.min(pointA.getBlockZ(), pointB.getBlockZ());
        this.x2 = Math.max(pointA.getBlockX(), pointB.getBlockX());
        this.y2 = Math.max(pointA.getBlockY(), pointB.getBlockY());
        this.z2 = Math.max(pointA.getBlockZ(), pointB.getBlockZ());
    }

    public Region(Location l1) {
        this(l1, l1);
    }

    public Region expandAllDirections(int amount) {
        return new Region(new Location(world,this.x1 - amount, this.y1 - amount, this.z1 - amount),new Location(world,this.x2 + amount, this.y2 + amount, this.z2 + amount));
    }

    public Region expandUp(int amount) {
        return new Region(new Location(world,this.x1 , this.y1, this.z1),new Location(world,this.x2, this.y2 + amount, this.z2));
    }

    private boolean contains(int x, int y, int z) {
        return x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2 && z >= this.z1 && z <= this.z2;
    }

    public boolean contains(Location l) {
        return this.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ()) && world.equals(l.getWorld());
    }

    public int getLowerX() {
        return this.x1;
    }

    public int getLowerY() {
        return this.y1;
    }

    public int getLowerZ() {
        return this.z1;
    }

    public int getUpperX() {
        return this.x2;
    }

    public int getUpperY() {
        return this.y2;
    }

    public int getUpperZ() {
        return this.z2;
    }

    public Location getCenter() {
        int x1 = this.getUpperX() + 1;
        int y1 = this.getUpperY() + 1;
        int z1 = this.getUpperZ() + 1;
        return new Location(world, this.getLowerX() + (x1 - this.getLowerX()) / 2.0, this.getLowerY() + (y1 - this.getLowerY()) / 2.0, this.getLowerZ() + (z1 - this.getLowerZ()) / 2.0);
    }

    public Location getLocationA(){
        return new Location(world, x1, y1, z1);
    }

    public Location getLocationB(){
        return new Location(world, x2, y2, z2);
    }

    public List<Location> getLocations(){
        List<Location> locations = new ArrayList<>();
        for(int x = getLowerX(); x <= getUpperX(); x++){
            for(int y = getLowerY(); y <= getUpperY(); y++){
                for(int z = getLowerZ(); z <= getUpperZ(); z++){
                    locations.add(new Location(world, x, y, z));
                }
            }
        }
        return locations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return x1 == region.x1 && y1 == region.y1 && z1 == region.z1 && x2 == region.x2 && y2 == region.y2 && z2 == region.z2 && Objects.equals(world.getName(), region.world.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(world.getName(), x1, y1, z1, x2, y2, z2);
    }
}
