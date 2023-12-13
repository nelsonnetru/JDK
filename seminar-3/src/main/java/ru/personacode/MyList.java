package ru.personacode;

import java.util.Iterator;

/*
Описать собственную коллекцию – список на основе массива. Коллекция должна иметь
возможность хранить любые типы данных, иметь методы добавления и удаления элементов.
 */
public class MyList<E> implements Iterable<E> {
    private final int DEFAULT_UPPER = 5;
    private E[] array;
    private int size;
    Object[] initialArray = {};

    public MyList() {
        this.array = (E[]) initialArray;
        size = 0;
    }

    public MyList(E[] baseArray) {
        this.array = baseArray;
        this.size = array.length;
    }

    public <T extends E> void addElement(T element) {
        if (size == array.length) {
            Object[] newArray = new Object[array.length + DEFAULT_UPPER];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[size] = element;
            this.array = (E[]) newArray;
        } else {
            array[size] = element;
        }
        size++;
    }

    public void removeElement(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;

    }

    public void printList() {
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    public class MyListIterator implements Iterator<E> {
        int index;

        public MyListIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            return array[index++];
        }

        @Override
        public void remove() {
        }
    }
}
