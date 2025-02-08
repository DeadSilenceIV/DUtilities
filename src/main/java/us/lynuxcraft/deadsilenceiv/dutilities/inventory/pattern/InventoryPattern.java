package us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern;

import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern.items.PatternItem;

import java.util.Map;
import java.util.Set;

public interface InventoryPattern{

    PatternItem getDefaultItem();

    char[] getBlueprint();

    Set<PatternItem> getItems();

    Map<Integer,PatternItem> getInventory();

    /**
     * Setups the pattern in a specified InteractiveInventory.
     *
     * @param inventory the inventory where the pattern will be setup.
     */
    default void setup(InteractiveInventory inventory){
        for (int i = 0; i < getInventorySize(); i++) {
            char symbol = getBlueprint()[i];
            PatternItem item = getItem(symbol);
            if(item == null || !item.setup(inventory,i)) {
                item = getDefaultItem();
                item.setup(inventory, i);
            }
        }
    }

    default void registerContent(){
        for (int i = 0; i < getInventorySize(); i++) {
            char symbol = getBlueprint()[i];
            PatternItem item = getItem(symbol);
            getInventory().put(i,item);
        }
    }

    /**
     * Gets a pattern item by its symbol.
     *
     * @param symbol the symbol of the pattern
     * @return the PatternItem instance, false if any item with the specified symbol was found.
     */
    default PatternItem getItem(char symbol){
        for (PatternItem item : getItems()) {
            if(item.getSymbol() == symbol)return item;
        }
        return null;
    }

    default PatternItem getItem(int slot){
        return getInventory().get(slot);
    }

    default int getInventorySize(){
        return getBlueprint().length;
    }

}
