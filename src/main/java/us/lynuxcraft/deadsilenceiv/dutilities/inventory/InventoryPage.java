package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import lombok.Getter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class InventoryPage extends BaseInteractiveInventory {
    protected Inventory inventory;
    @Getter protected int id;
    @Getter protected ItemStack[] initial;
    public InventoryPage(int id,ItemStack[] initial) {
        this.id = id;
        this.initial = initial;
    }

    protected abstract MultiPagesInventory<? extends InventoryPage> getOwner();

    protected abstract void setupPage();

    public void open(HumanEntity entity){
        entity.openInventory(getBukkitInventory());
    }

    @Override
    public Inventory getBukkitInventory() {
        return inventory;
    }
}
