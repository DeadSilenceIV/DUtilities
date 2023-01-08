package us.lynuxcraft.deadsilenceiv.dutilities.inventory.items;


import org.bukkit.plugin.java.JavaPlugin;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;

import javax.annotation.Nullable;

public interface ShareableItem extends InteractiveItem{

    void show(InteractiveInventory inventory);

    void refresh(@Nullable InteractiveInventory inventory);

    void refreshAsync(JavaPlugin plugin,@Nullable InteractiveInventory inventory);

}
