package us.lynuxcraft.deadsilenceiv.dutilities.inventory.buttons;


import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.items.ShareableItem;

import javax.annotation.Nullable;

public interface ShareableButton extends Button<ShareableItem> {

    default void show(String itemName, InteractiveInventory inventory){
        ShareableItem item = getItemByName(itemName);
        if(item != null){
            item.show(inventory);
            setCurrentItem(item);
        }
    }

    default void hide(ItemStack replacement,InteractiveInventory inventory){
        inventory.getBukkitInventory().setItem(getSlot(inventory),replacement);
        setCurrentItem(null);
    }

    default void refresh(@Nullable InteractiveInventory inventory){
        if(getCurrentItem() == null)return;
        getCurrentItem().refresh(inventory);
    }

    default void refreshAsync(JavaPlugin plugin,@Nullable InteractiveInventory inventory){
        if(getCurrentItem() == null)return;
        getCurrentItem().refreshAsync(plugin,inventory);
    }

    int getSlot(InteractiveInventory inventory);

}
