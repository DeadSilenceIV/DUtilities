package us.lynuxcraft.deadsilenceiv.dutilities.inventory.buttons.items;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.buttons.ShareableButton;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.items.ShareableItem;

public class ShareableButtonItem extends ButtonItem<ShareableButton> implements ShareableItem {
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
        ItemMeta updatedMeta = getUpdatedMeta();
        itemStack.setItemMeta(updatedMeta);
        if(inventory != null)setItem(inventory);
        initiallyRefreshed = true;
    }

    @Override
    public void refreshAsync(JavaPlugin plugin,InteractiveInventory inventory){
        Bukkit.getScheduler().runTaskAsynchronously(plugin,() -> {
            ItemMeta updatedMeta = getUpdatedMeta();
            Bukkit.getScheduler().runTask(plugin, () -> {
                itemStack.setItemMeta(updatedMeta);
                if(inventory != null)setItem(inventory);
                initiallyRefreshed = true;
            });
        });
    }

    public void setItem(InteractiveInventory inventory){
        inventory.getBukkitInventory().setItem(button.getSlot(inventory),itemStack);
    }

}
