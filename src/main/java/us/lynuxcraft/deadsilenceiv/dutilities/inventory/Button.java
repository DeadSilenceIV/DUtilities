package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import us.lynuxcraft.deadsilenceiv.dutilities.inventory.items.InteractiveItem;

import java.util.Set;

public interface Button<T extends InteractiveItem> {

    String getName();

    Set<T> getItems();

    default T getItemByName(String itemName){
        for (T item : getItems()) {
            if(item.getName().equals(itemName))return item;
        }
        return null;
    }

    default void addItem(T item){
        getItems().add(item);
    }

    default void removeItem(T item){
        getItems().remove(item);
    }

    void setCurrentItem(T item);

    T getCurrentItem();

}
