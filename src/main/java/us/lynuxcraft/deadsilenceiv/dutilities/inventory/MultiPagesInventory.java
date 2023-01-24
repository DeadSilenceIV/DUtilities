package us.lynuxcraft.deadsilenceiv.dutilities.inventory;

import lombok.Getter;
import org.bukkit.entity.HumanEntity;
import us.lynuxcraft.deadsilenceiv.dutilities.Pair;
import us.lynuxcraft.deadsilenceiv.dutilities.managers.InteractiveInventoryManager;

import java.util.*;

public abstract class MultiPagesInventory<T extends InventoryPage>{
    protected UUID uuid;
    @Getter protected int size;
    @Getter protected final Map<Integer,T> pages;
    protected Integer cachedHashCode;
    public MultiPagesInventory(){
        uuid = UUID.randomUUID();
        pages = new HashMap<>();
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
        this.size = size;
    }

    protected Pair<ResizeResult, Set<T>> resize(int newSize){
        int numberOfPages = (int) Math.floor((double) newSize/ (double) 45);
        int totalRows = (newSize/9);
        int pagesToCreate = (newSize % 45 == 0) ? (numberOfPages-1) : numberOfPages;
        int lastPageSlots = (newSize % 45 != 0) ? (totalRows-(numberOfPages*5))*9 : 45;
        ResizeResult result;
        Set<T> modified = new HashSet<>();
        if(numberOfPages > pages.size()){
            result = ResizeResult.ADDED_PAGES;
            for (int i = pages.size()-1; i < numberOfPages; i++) {
                T added = i < pagesToCreate ? newPage(i, 45) : newPage(i, lastPageSlots);
                pages.put(i,added);
                modified.add(added);
            }
        }else if(numberOfPages < pages.size()){
            result = ResizeResult.REMOVED_PAGES;
            int start = pages.size()-numberOfPages-1;
            System.out.println("start: "+start);
            for (int i = start; i < pages.size(); i++) {
                System.out.println(i);
                T removed = pages.remove(i);
                getInventoryManager().unRegister(removed);
                modified.add(removed);
            }
            changeLastPageSlots(lastPageSlots);
        }else{
            result = changeLastPageSlots(lastPageSlots);
            if(result == ResizeResult.LAST_PAGE_SLOTS_MODIFIED){
                modified.add(pages.get(pages.size()-1));
            }
        }
        this.size = newSize;
        return new Pair<>(result,modified);
    }

    protected ResizeResult changeLastPageSlots(int newAmount){
        int lastPageId = pages.size()-1;
        int currentLastPageSlots = pages.get(lastPageId).getBukkitInventory().getSize();
        if(currentLastPageSlots != newAmount){
            T removed = pages.remove(lastPageId);
            getInventoryManager().unRegister(removed);
            pages.put(lastPageId,newPage(lastPageId, newAmount));
            return ResizeResult.LAST_PAGE_SLOTS_MODIFIED;
        }
        return ResizeResult.NOTHING;
    }

    protected void loadPages(){
        for(T page : getPages().values()){
            page.setupPage();
        }
    }

    protected abstract T newPage(int id,int size);

    protected abstract InteractiveInventoryManager getInventoryManager();

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

    protected enum ResizeResult{
        ADDED_PAGES,
        REMOVED_PAGES,
        LAST_PAGE_SLOTS_MODIFIED,
        NOTHING
    }

}
