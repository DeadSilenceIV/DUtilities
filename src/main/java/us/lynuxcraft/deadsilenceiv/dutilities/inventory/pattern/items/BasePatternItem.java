package us.lynuxcraft.deadsilenceiv.dutilities.inventory.pattern.items;

import lombok.Getter;

public abstract class BasePatternItem implements PatternItem{
    @Getter private char symbol;
    public BasePatternItem(char symbol) {
        this.symbol = symbol;
    }
}
