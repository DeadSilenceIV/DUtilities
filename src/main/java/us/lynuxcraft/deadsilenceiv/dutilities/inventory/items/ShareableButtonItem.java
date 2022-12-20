package us.lynuxcraft.deadsilenceiv.dutilities.inventory.items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.ShareableButton;

public class ShareableButtonItem extends ButtonItem<ShareableButton> implements ShareableItem{
    public ShareableButtonItem(ShareableButton button, String name, ItemStack itemStack) {
        super(button, name, itemStack);
    }

    @Override
    public void show(InteractiveInventory inventory) {
        if(!initiallyRefreshed && !placeholders.isEmpty()){
            refresh(inventory);
        }else setItem(inventory);
    }

    @Override
    public void refresh(InteractiveInventory inventory) {
        ItemMeta updatedMeta = originalMeta.clone();
        updateName(updatedMeta);
        updateLore(updatedMeta);
        itemStack.setItemMeta(updatedMeta);
        if(inventory != null)setItem(inventory);
        initiallyRefreshed = true;
    }

    private void setItem(InteractiveInventory inventory){
        inventory.getBukkitInventory().setItem(button.getSlot(), itemStack.clone());
    }

}
