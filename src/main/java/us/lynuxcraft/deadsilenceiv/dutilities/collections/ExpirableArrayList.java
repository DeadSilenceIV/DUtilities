package us.lynuxcraft.deadsilenceiv.dutilities.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ExpirableArrayList<E> extends ArrayList<E> {

    private Long lastExpiration;

    private final long timeToClearInMs;

    public ExpirableArrayList(long timeToClearInMs) {
        this.timeToClearInMs = timeToClearInMs;
        lastExpiration = System.currentTimeMillis();
    }

    public ExpirableArrayList(long timeToClearInMs, Collection<? extends E> c) {
        super(c);
        this.timeToClearInMs = timeToClearInMs;
        lastExpiration = System.currentTimeMillis();
    }

    private void expire() {
        Long currentTime = System.currentTimeMillis();
        if (currentTime - lastExpiration > timeToClearInMs) {
            lastExpiration = currentTime;
            clear();
        }
    }

    @Override
    public int size() {
        expire();
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        expire();
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        expire();
        return super.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        expire();
        return super.iterator();
    }

    @Override
    public Object[] toArray() {
        expire();
        return super.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        expire();
        return super.toArray(a);
    }

    @Override
    public boolean add(E e) {
        expire();
        return super.add(e);
    }

    @Override
    public boolean remove(Object o) {
        expire();
        return super.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        expire();
        return super.contains(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        expire();
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        expire();
        return super.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        expire();
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        expire();
        return super.retainAll(c);
    }

    @Override
    public E get(int index) {
        expire();
        return super.get(index);
    }
}
