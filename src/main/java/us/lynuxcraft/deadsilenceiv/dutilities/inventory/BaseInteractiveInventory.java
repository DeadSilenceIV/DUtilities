package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.actions.InteractiveAction;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.actions.SlotAction;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.buttons.Button;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class BaseInteractiveInventory implements InteractiveInventory {
    protected Inventory inventory;
    @Getter protected final Set<Button> buttons;
    @Getter protected final Set<InteractiveAction> actions;
    @Getter protected final Map<Integer, Set<SlotAction>> slotActions;
    public BaseInteractiveInventory(){
        buttons = new HashSet<>();
        actions = new HashSet<>();
        slotActions = new HashMap<>();
    }

    @Override
    public void handleInventoryInteraction(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();
        int slot = event.getSlot();
        ClickType clickType = event.getClick();
        ItemStack currentItem = event.getCurrentItem();
        event.setCancelled(true);
        if(clickedInventory != null && clickedInventory.equals(getBukkitInventory()) && currentItem != null) {
            SlotAction slotAction = getSlotAction(slot,clickType);
            if(slotAction != null){
                slotAction.execute(player);
            }
        }
    }

    @Override
    public Inventory getBukkitInventory() {
        return inventory;
    }
}
