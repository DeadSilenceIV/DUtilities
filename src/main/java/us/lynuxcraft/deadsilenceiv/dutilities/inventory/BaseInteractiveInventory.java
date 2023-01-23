package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import lombok.Getter;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.actions.InteractiveAction;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.actions.SlotAction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class BaseInteractiveInventory implements InteractiveInventory {
    @Getter protected final Set<Button> buttons;
    @Getter protected final Set<InteractiveAction> actions;
    @Getter protected final Map<Integer, Set<SlotAction>> slotActions;
    public BaseInteractiveInventory(){
        buttons = new HashSet<>();
        actions = new HashSet<>();
        slotActions = new HashMap<>();
    }
}
