package us.lynuxcraft.deadsilenceiv.dutilities.collections;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

public class MaxSizeArrayList<E> extends ArrayList<E> {
    @Getter private final int maxSize;
    public MaxSizeArrayList(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(E e) {
        if(size() == maxSize){
            remove(0);
        }
        return super.add(e);
    }

    @Override
    public void add(int index, E element) {
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }
}
