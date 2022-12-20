package us.lynuxcraft.deadsilenceiv.dutilities.inventory;


import us.lynuxcraft.deadsilenceiv.dutilities.inventory.items.ShareableItem;

import javax.annotation.Nullable;

public interface ShareableButton extends Button<ShareableItem> {

    default void show(String itemName,InteractiveInventory inventory){
        ShareableItem item = getItemByName(itemName);
        if(item != null){
            item.show(inventory);
            setCurrentItem(item);
        }
    }

    default void refresh(@Nullable InteractiveInventory inventory){
        if(getCurrentItem() == null)return;
        getCurrentItem().refresh(inventory);
    }

}
