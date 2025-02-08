package us.lynuxcraft.deadsilenceiv.dutilities.inventory.buttons;

import lombok.Getter;
import lombok.Setter;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.items.InteractiveItem;

import java.util.HashSet;
import java.util.Set;

public class BaseButton<T extends InteractiveItem> implements Button<T> {
    @Getter protected final String name;
    @Getter protected final Set<T> items;
    @Getter @Setter protected T currentItem;
    public BaseButton(String name){
        this.name = name;
        this.items = new HashSet<>();
    }

}
