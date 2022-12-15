package us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern.items;

import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern.BaseInventoryPattern;

/**
 * This class represents an item inside the pattern content
 * {@link BaseInventoryPattern}.
 */
public interface PatternItem<I extends InteractiveInventory> {

    /**
     * Gets the symbol of the item.
     *
     * @return the symbol character of the item.
     */
    char getSymbol();

    /**
     * Setups the item in a specified InteractiveInventory.
     *
     * @param inventory the inventory where the item will be setup
     * @param slot the slot where the item will be setup
     * @return true if the item was correctly setup, false otherwise.
     */
    boolean setup(I inventory, int slot);
}
