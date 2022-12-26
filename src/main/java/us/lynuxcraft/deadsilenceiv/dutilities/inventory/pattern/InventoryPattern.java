package us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern;

import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern.items.PatternItem;

import java.util.List;

public interface InventoryPattern<I extends InteractiveInventory> {

    PatternItem<I> getDefaultItem();

    char[] getBlueprint();

    int getInventorySize();

    List<PatternItem<I>> getItems();

    /**
     * Setups the pattern in a specified InteractiveInventory.
     *
     * @param inventory the inventory where the pattern will be setup.
     */
    default void setup(I inventory){
        for (int i = 0; i < getInventorySize(); i++) {
            PatternItem<I> item = getItems().get(i);
            if(item != null && item.setup(inventory,i))continue;
            getDefaultItem().setup(inventory,i);
        }
    }

    /**
     * Gets a pattern item by its symbol.
     *
     * @param symbol the symbol of the pattern
     * @return the PatternItem instance, false if any item with the specified symbol was found.
     */
    default PatternItem<I> getItemBySymbol(char symbol){
        for (PatternItem<I> item : getItems()) {
            if(item.getSymbol() == symbol)return item;
        }
        return null;
    }

}
