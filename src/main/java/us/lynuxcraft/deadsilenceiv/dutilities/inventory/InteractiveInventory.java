package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.actions.*;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.buttons.Button;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.items.InteractiveItem;

import java.util.*;

public interface InteractiveInventory {

    Set<Button> getButtons();

    Set<InteractiveAction> getActions();

    Map<Integer,Set<SlotAction>> getSlotActions();

    default void addButton(Button button){
        getButtons().add(button);
    }

    default void removeButton(Button button){
        getButtons().remove(button);
    }

    default void addAction(InteractiveAction action){
        getActions().add(action);
        if(action instanceof SlotAction){
            SlotAction slotAction = (SlotAction)action;
            Set<SlotAction> actions = getSlotActions().getOrDefault(slotAction.getSlot(),new HashSet<>());
            actions.add(slotAction);
            getSlotActions().put(slotAction.getSlot(),actions);
        }
    }

    default void addActions(Collection<InteractiveAction> actions){
        for (InteractiveAction action : actions) {
            addAction(action);
        }
    }

    default void clearActions(){
        getActions().clear();
        getSlotActions().clear();
    }

    default void clearSlotActions(int... slots){
        for (int slot : slots) {
            Set<SlotAction> actions = getSlotActions().get(slot);
            if(actions != null){
                getActions().removeAll(actions);
                getSlotActions().remove(slot);
            }
        }
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
        Set<SlotAction> actions = getSlotActions().get(slot);
        if(actions != null){
            for (SlotAction action : actions) {
                if(action.getClickType() == type){
                    return action;
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

    default List<HumanEntity> getViewers(){
        if(getBukkitInventory() == null){
            return null;
        }
        return new ArrayList<>(getBukkitInventory().getViewers());
    }

    default void handleInventoryInteraction(InventoryClickEvent event){}

    default void handleInventoryDragging(InventoryDragEvent event){}

    default void handleInventoryOpening(InventoryOpenEvent event){}

    default void handleInventoryClosing(InventoryCloseEvent event){}


    default void open(Player player){
        player.openInventory(getBukkitInventory());
    }

    Inventory getBukkitInventory();

}
