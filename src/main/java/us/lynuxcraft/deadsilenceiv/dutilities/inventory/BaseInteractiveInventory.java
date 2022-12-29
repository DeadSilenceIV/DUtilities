package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import lombok.Getter;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.actions.InteractiveAction;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseInteractiveInventory implements InteractiveInventory {
    @Getter protected final Set<Button> buttons;
    @Getter protected final Set<InteractiveAction> actions;
    public BaseInteractiveInventory(){
        buttons = new HashSet<>();
        actions = new HashSet<>();
    }
}
