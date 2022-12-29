package us.lynuxcraft.deadsilenceiv.dutilities.inventory.actions;

import lombok.Getter;
import org.bukkit.event.inventory.ClickType;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.Button;

public abstract class ButtonAction extends InteractiveAction {
    @Getter private Button button;
    public ButtonAction(ClickType type,Button button) {
        super(type);
        this.button = button;
    }
}
