package us.lynuxcraft.deadsilenceiv.dutilities;

import lombok.Getter;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.Objects;

public class ChunkPosition {
    @Getter private final int x;
    @Getter private final int z;
    private Integer cachedHashCode;
    public ChunkPosition(Location location) {
        x = location.getBlockX() >> 4;
        z = location.getBlockZ() >> 4;
    }

    public ChunkPosition(Chunk chunk){
        x = chunk.getX();
        z = chunk.getZ();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChunkPosition location = (ChunkPosition) o;
        return x == location.x && z == location.z;
    }

    @Override
    public int hashCode() {
        if(cachedHashCode == null) {
            cachedHashCode = Objects.hash(x, z);
        }
        return cachedHashCode;
    }
}