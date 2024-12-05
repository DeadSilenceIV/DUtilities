package us.lynuxcraft.deadsilenceiv.dutilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

public class LocationUtils {

    private static final BlockFace[] axis = {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};

    public static final BlockFace[] radial = { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };

    public static String serializeLoc(Location l) {
        StringBuilder s = new StringBuilder();
        s.append("@w;").append(l.getWorld().getName());
        s.append(":@x;").append(l.getX());
        s.append(":@y;").append(l.getY());
        s.append(":@z;").append(l.getZ());
        s.append(":@pi;").append(l.getPitch());
        s.append(":@ya;").append(l.getYaw());
        return s.toString();
    }

    public static Location deserializeLoc(String s) {
        if(s != null && !s.isEmpty()) {
            Location l = new Location(Bukkit.getWorlds().get(0), 0, 0, 0, 0, 0);
            String[] att = s.split(":");
            for (String attribute : att) {
                String[] split = attribute.split(";");
                if (split[0].equalsIgnoreCase("@w"))
                    l.setWorld(Bukkit.getWorld(split[1]));
                if (split[0].equalsIgnoreCase("@x"))
                    l.setX(Double.parseDouble(split[1]));
                if (split[0].equalsIgnoreCase("@y"))
                    l.setY(Double.parseDouble(split[1]));
                if (split[0].equalsIgnoreCase("@z"))
                    l.setZ(Double.parseDouble(split[1]));
                if (split[0].equalsIgnoreCase("@pi"))
                    l.setPitch((float) Double.parseDouble(split[1]));
                if (split[0].equalsIgnoreCase("@ya"))
                    l.setYaw((float) Double.parseDouble(split[1]));

            }
            return l;
        }
        return null;
    }

    public static boolean verify(Location location){
        return (location != null && location.getWorld() != null);
    }


    public static BlockFace getDirection(Location location){
        return getDirection(location,false);
    }

    public static BlockFace getDirection(Location location,boolean useSubCardinalDirections){
        return yawToFace(location.getYaw(),useSubCardinalDirections);
    }

    private static BlockFace yawToFace(float yaw, boolean useSubCardinalDirections) {
        if (useSubCardinalDirections) {
            return radial[Math.round(yaw / 45f) & 0x7];
        } else {
            return axis[Math.round(yaw / 90f) & 0x3];
        }
    }

    public static float faceToYaw(BlockFace face) {
        if (face == BlockFace.NORTH) {
            return 180;
        } else if (face == BlockFace.EAST) {
            return 270;
        } else if (face == BlockFace.SOUTH) {
            return 360;
        } else if (face == BlockFace.WEST) {
            return 90;
        }
        return 0;
    }
}
