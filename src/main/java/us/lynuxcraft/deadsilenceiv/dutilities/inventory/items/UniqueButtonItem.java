package us.lynuxcraft.deadsilenceiv.dutilities.inventory.items;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.UniqueButton;

public class UniqueButtonItem extends ButtonItem<UniqueButton> implements UniqueItem{
    public UniqueButtonItem(UniqueButton button, String name, ItemStack itemStack) {
        super(button, name, itemStack);
    }

    @Override
    public void show() {
        if(!initiallyRefreshed && !placeholders.isEmpty()){
            refresh();
        }else button.getInventory().setItem(button.getSlot(), itemStack);
    }

    @Override
    public void refresh() {
        ItemMeta updatedMeta = originalMeta.clone();
        updateName(updatedMeta);
        updateLore(updatedMeta);
        itemStack.setItemMeta(updatedMeta);
        button.getInventory().setItem(button.getSlot(), itemStack);
        initiallyRefreshed = true;
    }

    @Override
    public void refreshAsync(JavaPlugin plugin){
        Bukkit.getScheduler().runTaskAsynchronously(plugin,() -> {
            ItemMeta updatedMeta = getUpdatedMeta();
            Bukkit.getScheduler().runTask(plugin,() -> {
                itemStack.setItemMeta(updatedMeta);
                button.getInventory().setItem(button.getSlot(), itemStack);
                initiallyRefreshed = true;
            });
        });
    }

    public void setItem(){
        button.getInventory().setItem(button.getSlot(), itemStack);
    }

}
