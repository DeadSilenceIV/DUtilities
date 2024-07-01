package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import us.lynuxcraft.deadsilenceiv.dutilities.NumberUtils;

public class InventoryUtils {

    public static int getSpacesForItem(Inventory inventory,ItemStack itemStack){
        int spaces = 0;
        int size = (inventory.getType() == InventoryType.PLAYER) ? inventory.getSize()-5 : inventory.getSize();
        boolean canBeStacked = itemStack.getMaxStackSize() > 1;
        for(int i = 0; i<= size-1;i++){
            ItemStack item = inventory.getItem(i);
            if(item == null){
                spaces += (canBeStacked) ? itemStack.getMaxStackSize() : 1;
            }else if(canBeStacked && item.isSimilar(itemStack)){
                spaces += (itemStack.getMaxStackSize()-item.getAmount());
            }
        }
        return spaces;
    }

    public static boolean hasSpaceForItem(Inventory inventory,ItemStack itemStack){
        int size = (inventory.getType() == InventoryType.PLAYER) ? inventory.getSize()-5 : inventory.getSize();
        int maxStackSize = itemStack.getMaxStackSize();
        for(int i = 0; i<= size-1;i++){
            ItemStack item = inventory.getItem(i);
            if(item == null){
                return true;
            }else if((item.getAmount()+itemStack.getAmount()) <= maxStackSize && item.isSimilar(itemStack)){
                return true;
            }
        }
        return false;
    }

    public static Integer getFirstEmptySlot(Inventory inventory){
        for (int i = 0; i < inventory.getSize(); i++) {
            if(inventory.getItem(i) == null)return i;
        }
        return null;
    }

    public static int remove(Inventory inventory, Material material,int limit){
        int deletedItems = 0;
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if(item != null && item.getType() == material && !item.hasItemMeta()){
                int amountToDelete = item.getAmount();
                if(limit > 0 && deletedItems+amountToDelete >= limit){
                    amountToDelete = limit-deletedItems;
                    deletedItems += amountToDelete;
                    item.setAmount(item.getAmount()-amountToDelete);
                    break;
                }
                deletedItems += amountToDelete;
                item.setAmount(0);
            }
        }
        return deletedItems;
    }

}
