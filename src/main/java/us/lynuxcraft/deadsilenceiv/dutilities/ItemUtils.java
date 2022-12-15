package us.lynuxcraft.deadsilenceiv.dutilities;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import us.lynuxcraft.deadsilenceiv.dutilities.builders.ItemBuilder;
import us.lynuxcraft.deadsilenceiv.dutilities.builders.SkullBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ItemUtils {

    private static final LoadingCache<Short, ItemStack> backgrounds = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.MINUTES).build(new CacheLoader<Short, ItemStack>(){
        @Override
        public ItemStack load(Short color){
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
            return new ItemBuilder(material.parseItem()).setName(""+ ChatColor.ITALIC+ChatColor.RESET).build();
        }
    });

    public static ItemStack getItemStack(XMaterial material, String name, String headName,List<String> lore){
        ItemStack item;
        if(material == XMaterial.PLAYER_HEAD){
            item = new SkullBuilder()
                    .setOwner(headName)
                    .setName(name)
                    .setLore(lore)
                    .build();
        }else {
            item = new ItemBuilder(material.parseItem())
                    .setName(name)
                    .setLore(lore)
                    .build();
        }
        return item;
    }

    public static ItemStack getInventoryBackground(short color){
        return backgrounds.getUnchecked(color);
    }

}
