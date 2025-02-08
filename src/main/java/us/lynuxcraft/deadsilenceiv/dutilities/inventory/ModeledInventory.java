package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern.BaseInventoryPattern;

public abstract class ModeledInventory extends BaseInteractiveInventory{

    protected void setupInventory(){
        BaseInventoryPattern pattern = getPattern();
        String inventoryName = ChatColor.translateAlternateColorCodes('&',getInventoryName());
        inventory = Bukkit.createInventory(null,pattern.getInventorySize(), inventoryName);
        pattern.setup(this);
    }

    protected abstract BaseInventoryPattern getPattern();

    protected abstract String getInventoryName();
}
