package us.lynuxcraft.deadsilenceiv.dutilities;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ConfigUtils {

    public static ItemStack getIcon(ConfigurationSection main){
        Material material;
        try {
            material = Material.valueOf(main.getString("type","STONE"));
        }catch (IllegalArgumentException e){
            material = Material.STONE;
        }
        String name = main.getString("name");
        String head = main.getString("head");
        List<String> lore = main.getStringList("lore");
        Integer customModelData = (main.isSet("custom-model-data")) ? main.getInt("custom-model-data") : null;
        int amount = main.getInt("amount",1);
        ItemStack item = ItemUtils.getItemStack(material,name,head,lore,customModelData);
        item.setAmount(amount);
        return item;
    }

}
