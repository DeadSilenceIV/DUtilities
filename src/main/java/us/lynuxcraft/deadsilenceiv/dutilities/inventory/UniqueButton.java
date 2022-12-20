package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import org.bukkit.inventory.Inventory;
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

    default void refresh(){
        if(getCurrentItem() == null)return;
        getCurrentItem().refresh();
    }

}
