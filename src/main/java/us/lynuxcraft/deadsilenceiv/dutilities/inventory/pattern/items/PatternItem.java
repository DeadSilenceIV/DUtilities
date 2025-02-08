package us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern.items;

import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;

/**
 * This class represents an item inside the pattern content of an InteractiveInventory.
 */
public interface PatternItem{

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
    boolean setup(InteractiveInventory inventory, int slot);
}
