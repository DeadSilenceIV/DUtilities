package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import com.cryptomorin.xseries.XMaterial;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import us.lynuxcraft.deadsilenceiv.dutilities.builders.ItemBuilder;
import us.lynuxcraft.deadsilenceiv.dutilities.builders.SkullBuilder;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.items.InteractiveItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseButton<T extends InteractiveItem> implements Button<T> {
    @Getter protected final String name;
    @Getter protected final int slot;
    @Getter protected final Set<T> items;
    @Getter @Setter protected T currentItem;
    public BaseButton(String name,int slot) {
        this.name = name;
        this.slot = slot;
        this.items = new HashSet<>();
    }

    protected ItemStack getItemStack(Material material, String name, String headName , List<String> lore, Integer customModelData){
        ItemStack item;
        if(material == XMaterial.PLAYER_HEAD.parseMaterial()){
            item = new SkullBuilder()
                    .setOwner(headName)
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
