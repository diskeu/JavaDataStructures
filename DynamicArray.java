package DynamicArray;

import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class DynamicArray<E> implements List<E> {
    private Object[] currentList;
    private int count = 0;

    private class ArrayIterator implements Iterator<E> {
        private int count = 0;
        private int maxCount;

        public ArrayIterator(int maxCount) {
            this.maxCount = maxCount; 
        }

        public boolean hasNext() {
            return count < maxCount;
        }
        public E next() {
            try {
                return get(++count);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }
    }
    private class ArrayListIterator implements ListIterator<E> {
        private int[] cursor = {-1, 0}; // [prev, next]

        public ArrayListIterator() { }
        public ArrayListIterator(int index) {
            cursor[0] = index-1;
            cursor[1] = index;
        }
        @Override
        public boolean hasNext() {
            return cursor[1] < count;
        }
        @Override
        public boolean hasPrevious() {
            return cursor[0] >= 0;
        }
        @Override
        @SuppressWarnings("unchecked")
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            E element = (E) currentList[cursor[0]];
            cursor[0]--; cursor[1]--;
            return element;
        }
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = (E) currentList[cursor[1]];
            cursor[0]++; cursor[1]++;
            return element;
        }
        @Override
        public int nextIndex() {
            return hasNext() ? cursor[1] : count;
        }
        @Override
        public int previousIndex() {
            return hasPrevious() ? cursor[0] : -1;
        }
        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public void set(E e) {
            throw new UnsupportedOperationException();
        }
    }
    @SuppressWarnings("unchecked")
    public DynamicArray(E... e) {
        // Creates new Array with double size
        this.currentList = new Object[e.length*2];
        for (int i = 0; i < e.length; i++) {
            this.currentList[i] = e[i];
        }
        this.count = e.length;
    }
    public DynamicArray() {
        // Creates new Array with size = 2
        this.currentList = new Object[2];
    }
    private DynamicArray(Object[] array, int count) {
        /* Creates a new Array from an existing
        Dynamic Array intern Array*/
        this.currentList = array;
        this.count = count;
    }
    private boolean doubleSize() {
        // Create new Array with double size
        // returns if the Array got doubled
        if (count >= currentList.length) {
            Object[] oldArray = (Object[]) this.currentList.clone();
            this.currentList = new Object[oldArray.length*2];
            for (int i = 0; i < oldArray.length; i++) {
                this.currentList[i] = oldArray[i];
            }
            this.count = oldArray.length;
            return true;
        }
        return false;
    }
    @Override
    public boolean add(E e) {
        doubleSize();
        this.currentList[this.count] = (Object) e;
        this.count++;
        return true;
    }
    @Override
    public void add(int index, E element) {
        // Adds element to specific index.
        // 0 <= Index <= Array.length.
        if (index > this.count)
            throw new IndexOutOfBoundsException("Index has to be 0 <= Index <= Array.length");

        doubleSize();
        for (int i = this.count; i >= index; i--) {
            this.currentList[i] = this.currentList[i+1];
        }
        this.currentList[index] = (Object) element;
        this.count++;
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not Implemented");
    }
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not Implemented");
    }
    @Override
    public void clear() {
        for (int i = 0; i < this.count; i++) {
            this.currentList[i] = null;
        }
        this.count = 0;
    }
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < this.count; i++) {
            if (this.currentList[i].equals(o)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean containsAll(Collection<?> c) 
    {
        throw new UnsupportedOperationException("Not Implemented");
    }
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= this.count || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bound");
        }
        return (E) this.currentList[index];
    }
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.count; i++) {
            if (this.currentList[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator(this.count);
    }
    @Override
    public int lastIndexOf(Object o) {
        int lastI = -1;
        for (int i = 0; i < this.count; i++) {
            if (this.currentList[i].equals(o))
                lastI = i;
        }
        return lastI;
    }
    // @Override
    // public ListIterator<E> listIterator() {
    //     throw new UnsupportedOperationException("Not Implemented");
    //     return;
    // }
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index >= this.count || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        E o = (E) this.currentList[index];
        for (int i = index+1; i < this.count; i++) {
            this.currentList[i-1] = this.currentList[i];
        }
        this.count--;
        return o;
    }
    @Override
    public boolean remove(Object o) {
        try {
            int index = indexOf(o);
            if (index != -1) {
                remove(index);
                return true;
            }
            return false;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not Implemented");
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not Implemented");
    }
    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException();
        }
        Object prev = this.currentList[index];
        this.currentList[index] = element;
        return (E) prev;
    }
    @Override
    public int size() {
        return this.count;
    }
    @Override
    public List<E> subList(int fromI, int toI) {
        if (
            (fromI < 0 || fromI >= this.count)
            || (toI > this.count || toI <= fromI)
        ) {
            throw new IndexOutOfBoundsException();
        }
        return new DynamicArray<E>(this.currentList, this.count);
    }
    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[this.count];
        for (int i = 0; i < this.count; i++) {
            newArray[i] = this.currentList[i];
        }
        return newArray;
    }
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length != this.count) {
            Class<?> type = a.getClass().getComponentType();
            a = (T[]) Array.newInstance(type, this.count);
        }
        for (int i = 0; i < this.count; i++) {
            a[i] = (T) this.currentList[i];
        }
        return a;
    }
    @Override
    public ListIterator<E> listIterator() {
        return new ArrayListIterator();
    }
    @Override
    public ListIterator<E> listIterator(int index) {
        return new ArrayListIterator(index);
    }
}