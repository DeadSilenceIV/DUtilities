package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.items.UniqueItem;

public interface UniqueButton extends Button<UniqueItem> {

    Inventory getInventory();

    default void show(String itemName){
        UniqueItem item = getItemByName(itemName);
        if(item != null){
            item.show();
            setCurrentItem(item);
        }
    }

    default void hide(ItemStack replacement){
        getInventory().setItem(getSlot(),replacement);
        setCurrentItem(null);
    }

    default void refresh(){
        if(getCurrentItem() == null)return;
        getCurrentItem().refresh();
    }

    default void refreshAsync(JavaPlugin plugin){
        if(getCurrentItem() == null)return;
        getCurrentItem().refreshAsync(plugin);
    }

    void setSlot(int slot);

    int getSlot();

}
