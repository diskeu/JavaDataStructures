package DynamicArray;

import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.IndexOutOfBoundsException;
import java.util.UnsupportedOperationException;

public class DynamicArray<E> implements List<E> {
    private E[] currentList;
    private int count = 0;

    private class ArrayIterator<E> implements Iterator<E>{
        private int count = 0;

        public boolean hasNext() {
            return count < DynamicArray.this.count;
        }
        public E next() {
            try {
                return get(++count);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }
    }
    public DynamicArray(E... e) {
        // Creates new Array with double size
        this.currentList = new E[e.length*2];
        for (int i = 0; i < e.length; i++) {
            this.currentList[i] = e[i];
        }
        this.count = e.length;
    }
    public DynamicArray() {
        // Creates new Array with size = 2
        this.currentList = new E[2];
    }
    @Override
    private void doubleSize() {
        // Create new Array with double size
        // returns if the Array got doubled
        if (count >= currentList.length) {
            oldArray = this.currentList.clone();
            this.currentList = E[oldArray.length*2];
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
        this.currentList[this.count] = e;
        this.count++;
        return true;
    }
    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        // Adds element to specific index.
        // 0 <= Index <= Array.length.
        if (index > this.count)
            throw new IndexOutOfBoundsException("Index has to be 0 <= Index <= Array.length");

        doubleSize();
        for (int i = this.count; i >= index; i--) {
            this.currentList[i] = this.currentList[i+1];
        }
        this.currentList[index] = element;
        this.count++;
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not Implemented");
        return false;
    }
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not Implemented");
        return false;
    }
    @Override
    public void clear() {
        for (int i = 0; i < this.count; i++) {
            this.currentList[i] = null;
        }
        this.count = 0;
        return true;
    }
    @override
    public boolean contains(Object o) {
        for (int i = 0; i < this.count; i++) {
            if (this.currentList[i].equals(o)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean containsAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not Implemented");
        return false;
    }
    @Override
    public E get(int index) {
        if (index >= this.count || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bound");
        }
        return this.currentList[index];
    }
    @Override
    public int indexOf(Object o) {
        if (!o instanceof E)
            return -1;
        for (int i = 0; i < this.count; i++) {
            if (this.currentList[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public boolean isEmpty() {
        return x.count == 0;
    }
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }
    @Override
    public int lastIndexOf(Object o) {
        int lastI = -1;
        if (!o instanceof E)
            return lastI;
        for (int i = 0; i < this.count; i++) {
            if (this.currentList[i].equals(o))
                lastI = i;
        }
        return lastI;
    }
    @Override
    public listIterator<E> listIterator() {
        
    }

}