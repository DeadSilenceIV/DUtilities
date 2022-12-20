package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import lombok.Getter;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.actions.InventoryAction;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseInteractiveInventory implements us.lynuxcraft.deadsilenceiv.dutilities.inventory.InteractiveInventory {
    @Getter protected final Set<Button> buttons;
    @Getter protected final Set<InventoryAction> actions;
    public BaseInteractiveInventory(){
        buttons = new HashSet<>();
        actions = new HashSet<>();
    }
}
