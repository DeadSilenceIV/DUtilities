package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.actions.*;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.items.InteractiveItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface InteractiveInventory {

    Set<Button> getButtons();

    Set<InteractiveAction> getActions();

    default void addButton(Button button){
        getButtons().add(button);
    }

    default void addAction(InteractiveAction action){
        getActions().add(action);
    }

    default void clearActions(){
        getActions().clear();
    }

    default Button getButtonByName(String name){
        for (Button button : getButtons()) {
            if(button.getName().equals(name))return button;
        }
        return null;
    }

    default Button getButton(ItemStack item){
        for (Button button : getButtons()) {
            InteractiveItem currentItem = button.getCurrentItem();
            if(currentItem != null) {
                if (currentItem.getItemStack().isSimilar(item))return button;
            }
        }
        return null;
    }

    default ButtonAction getButtonAction(Button button, ClickType type){
        for (InteractiveAction action : getActions()) {
            if(action instanceof ButtonAction){
                ButtonAction buttonAction = (ButtonAction)action;
                if(buttonAction.getButton().equals(button) && buttonAction.getClickType() == type){
                    return buttonAction;
                }
            }
        }
        return null;
    }

    default SlotAction getSlotAction(int slot, ClickType type){
        for(InteractiveAction action : getActions()){
            if(action instanceof SlotAction) {
             SlotAction slotAction = (SlotAction)action;
                if (slot == slotAction.getSlot() && action.getClickType() == type) {
                    return slotAction;
                }
            }
        }
        return null;
    }

    default ItemAction getItemAction(ItemStack stack, ClickType type){
        for(InteractiveAction action : getActions()){
            if(action instanceof ItemAction) {
                ItemAction itemAction = (ItemAction)action;
                if(itemAction.getStack().isSimilar(stack) && action.getClickType() == type)return itemAction;
            }
        }
        return null;
    }

    default OutSideAction getOutSideAction(ClickType clickType){
        for (InteractiveAction action : getActions()) {
            if(action instanceof OutSideAction){
                OutSideAction outSideAction = (OutSideAction)action;
                if(outSideAction.getClickType().equals(clickType)){
                    return outSideAction;
                }
            }
        }
        return null;
    }

    default void closeForViewers(){
        if(getBukkitInventory() == null)return;
        List<HumanEntity> viewers = new ArrayList<>(getBukkitInventory().getViewers());
        for (int i = 0; i < viewers.size(); i++){
            viewers.get(i).closeInventory();
        }
    }

    void handleInventoryInteraction(InventoryClickEvent event);

    default void handleInventoryDragging(InventoryDragEvent event){}

    Inventory getBukkitInventory();

}
