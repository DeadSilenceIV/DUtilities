package us.lynuxcraft.deadsilenceiv.dutilities.inventory.items;

import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;

import javax.annotation.Nullable;

public interface ShareableButtonItem extends ButtonItem,ShareableItem{

    void show(InteractiveInventory inventory);

    void refresh(@Nullable InteractiveInventory inventory);

}
