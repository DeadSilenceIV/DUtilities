package us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern;

import lombok.Getter;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern.items.PatternItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class BaseInventoryPattern<I extends InteractiveInventory> implements InventoryPattern<I>{
    @Getter protected char[] blueprint;
    @Getter protected Set<PatternItem<I>> items;
    @Getter protected Map<Integer,PatternItem<I>> inventory;
    @Getter protected PatternItem<I> defaultItem;
    public BaseInventoryPattern(char[] blueprint, Set<PatternItem<I>> items, PatternItem<I> defaultItem) {
        this.blueprint = blueprint;
        this.items = items;
        this.inventory = new HashMap<>();
        this.defaultItem = defaultItem;
    }
}
