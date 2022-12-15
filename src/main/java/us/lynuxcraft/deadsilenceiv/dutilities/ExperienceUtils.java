package us.lynuxcraft.deadsilenceiv.dutilities;

import org.bukkit.entity.Player;

public class ExperienceUtils {
    private static int result;
    private static void setEXP(int level, float exp) {
        if (level > 30) {
            double xplevel = 4.5D * level * level - 162.5D * level + 2220.0D;
            int xp = 9 * level - 158;
            result = ((int)(xplevel += Math.round(exp * xp)));
        }else if (level > 15) {
            double xplevel = 2.5D * level * level - 40.5D * level + 360.0D;
            int xp = 5 * level - 38;
            result = ((int)(xplevel += Math.round(exp * xp)));
        }else{
            double xplevel = level * level + 6 * level;
            int xp = 2 * level + 7;
            result = ((int)(xplevel += Math.round(exp * xp)));
        }
    }

    public static int getExp(Player p) {
        setEXP(p.getLevel(), p.getExp());
        return result;
    }

    public static void addExp(Player p, int value) {
        int xp = getExp(p);
        p.setTotalExperience(0);
        p.setLevel(0);
        p.setExp(0.0F);
        p.giveExp(xp += value);
    }

    public static void removeExp(Player p, int value) {
        int xp = getExp(p);
        p.setTotalExperience(0);
        p.setLevel(0);
        p.setExp(0.0F);
        p.giveExp(xp -= value);
    }
}
