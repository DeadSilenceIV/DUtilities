package us.lynuxcraft.deadsilenceiv.dutilities.inventory.actions;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public abstract class InteractiveAction {
    @Getter private ClickType clickType;
    InteractiveAction(ClickType type){
        this.clickType = type;
    }

    /**
     * Executes the developed action.
     *
     * @param player the player instance.
     */
    public abstract void execute(Player player);
}
