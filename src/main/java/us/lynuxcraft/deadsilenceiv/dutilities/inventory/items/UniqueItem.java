package us.lynuxcraft.deadsilenceiv.dutilities.inventory.items;

import org.bukkit.plugin.java.JavaPlugin;

public interface UniqueItem extends InteractiveItem{

    void show();

    void refresh();

    void refreshAsync(JavaPlugin plugin);

}
