package us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern.items;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.inventory.ItemStack;
import us.lynuxcraft.deadsilenceiv.dutilities.ItemUtils;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;

import java.util.List;

public class NormalItem extends BasePatternItem<InteractiveInventory> {
    private ItemStack item;
    public NormalItem(char symbol, XMaterial material, String name, String headName, List<String> lore) {
        super(symbol);
        item = ItemUtils.getItemStack(material,name,headName,lore);
    }

    @Override
    public boolean setup(InteractiveInventory inventory, int slot) {
        inventory.getBukkitInventory().setItem(slot,item);
        return true;
    }
}
