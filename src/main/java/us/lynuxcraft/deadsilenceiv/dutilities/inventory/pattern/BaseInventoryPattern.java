package us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern;

import lombok.Getter;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern.items.PatternItem;

import java.util.List;

public abstract class BaseInventoryPattern<I extends InteractiveInventory> implements InventoryPattern<I>{
    @Getter protected char[] blueprint;
    @Getter protected int inventorySize;
    @Getter protected List<PatternItem<I>> items;
    @Getter protected PatternItem<I> defaultItem;
    public BaseInventoryPattern(char[] blueprint, List<PatternItem<I>> items, PatternItem<I> defaultItem) {
        this.blueprint = blueprint;
        this.inventorySize = blueprint.length;
        this.items = items;
        this.defaultItem = defaultItem;
    }
}
