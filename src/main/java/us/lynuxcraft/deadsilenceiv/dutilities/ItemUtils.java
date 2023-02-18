package us.lynuxcraft.deadsilenceiv.dutilities;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import us.lynuxcraft.deadsilenceiv.dutilities.builders.ItemBuilder;
import us.lynuxcraft.deadsilenceiv.dutilities.builders.SkullBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ItemUtils {
    private static final Map<Short, ItemStack> CACHED_PANELS = new HashMap<>();

    public static ItemStack getInventoryBackground(short color){
        return getInventoryBackground(color,""+ChatColor.ITALIC+ChatColor.RESET);
    }

    @SuppressWarnings("all")
    public static ItemStack getInventoryBackground(short color,String name){
        if(!CACHED_PANELS.containsKey(color)){
            XMaterial material;
            switch (color){
                case 0:
                    material = XMaterial.WHITE_STAINED_GLASS_PANE;
                    break;
                case 1:
                    material = XMaterial.ORANGE_STAINED_GLASS_PANE;
                    break;
                case 2:
                    material = XMaterial.MAGENTA_STAINED_GLASS_PANE;
                    break;
                case 3:
                    material = XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE;
                    break;
                case 4:
                    material = XMaterial.YELLOW_STAINED_GLASS_PANE;
                    break;
                case 5:
                    material = XMaterial.LIME_STAINED_GLASS_PANE;
                    break;
                case 6:
                    material = XMaterial.PINK_STAINED_GLASS_PANE;
                    break;
                case 7:
                    material = XMaterial.GRAY_STAINED_GLASS_PANE;
                    break;
                case 8:
                    material = XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE;
                    break;
                case 9:
                    material = XMaterial.CYAN_STAINED_GLASS_PANE;
                    break;
                case 10:
                    material = XMaterial.PURPLE_STAINED_GLASS_PANE;
                    break;
                case 11:
                    material = XMaterial.BLUE_STAINED_GLASS_PANE;
                    break;
                case 12:
                    material = XMaterial.BROWN_STAINED_GLASS_PANE;
                    break;
                case 13:
                    material = XMaterial.GREEN_STAINED_GLASS_PANE;
                    break;
                case 14:
                    material = XMaterial.RED_STAINED_GLASS_PANE;
                    break;
                default:
                    material = XMaterial.BLACK_STAINED_GLASS_PANE;
            }
            ItemStack item =  new ItemBuilder(material.parseItem()).setName(name).build();
            CACHED_PANELS.put(color,item);
            return item;
        }else{
            return CACHED_PANELS.get(color);
        }
    }

    public static ItemStack getItemStack(Material material, String name, String headName, List<String> lore, Integer customModelData){
        ItemStack item;
        if(material == XMaterial.PLAYER_HEAD.parseMaterial()){
            item = new SkullBuilder(headName)
                    .setName(name)
                    .setLore(lore)
                    .setCustomModelData(customModelData)
                    .build();
        }else {
            item = new ItemBuilder(material)
                    .setName(name)
                    .setLore(lore)
                    .setCustomModelData(customModelData)
                    .build();
        }
        return item;
    }

}
