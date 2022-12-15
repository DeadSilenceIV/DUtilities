package us.lynuxcraft.deadsilenceiv.dutilities.inventory.items;

import us.lynuxcraft.deadsilenceiv.dutilities.ItemPlaceHolder;

import java.util.Set;

public interface ButtonItem extends InteractiveItem{

    String getName();

    Set<ItemPlaceHolder> getPlaceholders();

    void addPlaceholder(ItemPlaceHolder placeholder);

}
