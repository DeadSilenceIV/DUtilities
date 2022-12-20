package us.lynuxcraft.deadsilenceiv.dutilities.inventory.items;

import org.bukkit.inventory.ItemStack;
import us.lynuxcraft.deadsilenceiv.dutilities.Placeholder;

import java.util.Set;

public interface InteractiveItem{

    String getName();

    ItemStack getItemStack();

    Set<Placeholder> getPlaceholders();

    void addPlaceholder(Placeholder placeholder);

}
