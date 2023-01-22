package us.lynuxcraft.deadsilenceiv.dutilities.managers;

import lombok.Getter;
import org.bukkit.inventory.Inventory;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;

import java.util.HashMap;
import java.util.Map;

public class InteractiveInventoryManager {
    @Getter private final Map<Inventory, InteractiveInventory> inventories;
    public InteractiveInventoryManager() {
        inventories = new HashMap<>();
    }

    /**
     * Registers an InteractiveInventory.
     *
     * @param inventory the InteractiveInventory instance.
     */
    public void register(InteractiveInventory inventory){
        inventories.put(inventory.getBukkitInventory(),inventory);
    }

    /**
     * Un-Registers an InteractiveInventory.
     *
     * @param inventory the InteractiveInventory instance.
     */
    public void unRegister(InteractiveInventory inventory){
        inventories.remove(inventory.getBukkitInventory());
    }

    /**
     * Gets the {@link InteractiveInventory} by the specified {@link Inventory}.
     *
     * @param inventory the bukkit inventory
     * @return the InteractiveInventory linked, null if there isn't an InteractiveInventory linked.
     */
    public InteractiveInventory getInteractiveByBukkit(Inventory inventory){
        return inventories.get(inventory);
    }

}
