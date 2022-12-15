package us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern.items;

import lombok.Getter;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory;

public abstract class BasePatternItem<I extends InteractiveInventory> implements PatternItem<I> {
    @Getter private char symbol;
    public BasePatternItem(char symbol) {
        this.symbol = symbol;
    }
}
