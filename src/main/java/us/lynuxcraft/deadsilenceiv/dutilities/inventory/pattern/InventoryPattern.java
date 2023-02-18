package us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern;

import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern.items.PatternItem;

import java.util.Map;
import java.util.Set;

public interface InventoryPattern<I extends InteractiveInventory> {

    PatternItem<I> getDefaultItem();

    char[] getBlueprint();

    Set<PatternItem<I>> getItems();

    Map<Integer,PatternItem<I>> getInventory();

    /**
     * Setups the pattern in a specified InteractiveInventory.
     *
     * @param inventory the inventory where the pattern will be setup.
     */
    default void setup(I inventory){
        for (int i = 0; i < getInventorySize(); i++) {
            char symbol = getBlueprint()[i];
            PatternItem<I> item = getItem(symbol);
            if(item == null || !item.setup(inventory,i)) {
                item = getDefaultItem();
                item.setup(inventory, i);
            }
        }
    }

    default void registerContent(){
        for (int i = 0; i < getInventorySize(); i++) {
            char symbol = getBlueprint()[i];
            PatternItem<I> item = getItem(symbol);
            getInventory().put(i,item);
        }
    }

    /**
     * Gets a pattern item by its symbol.
     *
     * @param symbol the symbol of the pattern
     * @return the PatternItem instance, false if any item with the specified symbol was found.
     */
    default PatternItem<I> getItem(char symbol){
        for (PatternItem<I> item : getItems()) {
            if(item.getSymbol() == symbol)return item;
        }
        return null;
    }

    default PatternItem<I> getItem(int slot){
        return getInventory().get(slot);
    }

    default int getInventorySize(){
        return getBlueprint().length;
    }

}
