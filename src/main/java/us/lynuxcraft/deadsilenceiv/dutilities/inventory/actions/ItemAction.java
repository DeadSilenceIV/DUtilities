package us.lynuxcraft.deadsilenceiv.dutilities.inventory.actions;

import lombok.Getter;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public abstract class ItemAction extends InteractiveAction {
    @Getter private ItemStack stack;
    public ItemAction(ClickType type, ItemStack stack) {
        super(type);
        this.stack = stack.clone();
    }
}
