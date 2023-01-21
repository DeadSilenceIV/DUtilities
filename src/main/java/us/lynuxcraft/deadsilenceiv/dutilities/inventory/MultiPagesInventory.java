package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import lombok.Getter;
import org.bukkit.entity.HumanEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public abstract class MultiPagesInventory<T extends InventoryPage>{
    protected UUID uuid;
    protected int size;
    @Getter protected final Map<Integer,T> pages;
    protected Integer cachedHashCode;
    public MultiPagesInventory(int size){
        uuid = UUID.randomUUID();
        pages = new HashMap<>();
        this.size = size;
    }

    public void openPage(HumanEntity entity, int id){
        getPageById(id).open(entity);
    }

    protected void createPages(int size){
        int numberOfPages = (int) Math.floor((double) size/ (double) 45);
        int totalRows = (size/9);
        if(size>45) {
            int pagesToCreate = (size % 45 == 0) ? (numberOfPages-1) : numberOfPages;
            int lastPageSlots = (size % 45 != 0) ? (totalRows-(numberOfPages*5))*9 : 45;
            for (int i = 0; i <= pagesToCreate; i++){
                pages.put(i,i < pagesToCreate ? newPage(i, 45) : newPage(i, lastPageSlots));
            }
        }else{
            pages.put(0,newPage(0, size));
        }
    }

    protected void loadPages(){
        for(T page : getPages().values()){
            page.setupPage();
        }
    }

    protected abstract T newPage(int id,int size);

    public T getPageById(int id){
        return getPages().get(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultiPagesInventory)) return false;
        MultiPagesInventory<?> that = (MultiPagesInventory<?>) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        if(cachedHashCode == null) {
            cachedHashCode =  Objects.hash(uuid);
        }
        return cachedHashCode;
    }
}
