package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import lombok.Getter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;

public abstract class InventoryPage extends BaseInteractiveInventory {
    protected Inventory inventory;
    @Getter protected int id;
    @Getter protected int size;
    public InventoryPage(int id,int size) {
        this.id = id;
        this.size = size;
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
