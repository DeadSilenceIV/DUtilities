package us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern;

import lombok.Getter;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern.items.PatternItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BaseInventoryPattern implements InventoryPattern{
    @Getter protected char[] blueprint;
    @Getter protected Set<PatternItem> items;
    @Getter protected Map<Integer,PatternItem> inventory;
    @Getter protected PatternItem defaultItem;
    public BaseInventoryPattern(char[] blueprint, Set<PatternItem> items, PatternItem defaultItem) {
        this.blueprint = blueprint;
        this.items = items;
        this.inventory = new HashMap<>();
        this.defaultItem = defaultItem;
        registerContent();
    }
}
