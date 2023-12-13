package ru.personacode;

import java.io.DataInput;
import java.io.InputStream;

public class MegaClass<T extends Comparable<?>, V extends InputStream & DataInput, K extends Number> {
    T t;
    V v;
    K k;

    public MegaClass(T t, V v, K k) {
        this.t = t;
        this.v = v;
        this.k = k;
    }

    public T getT() {
        return t;
    }

    public V getV() {
        return v;
    }

    public K getK() {
        return k;
    }

    void printName() {
        System.out.println("t: " + t.getClass().getSimpleName() + "\n" +
                "v: " + v.getClass().getSimpleName() + "\n" +
                "k: " + k.getClass().getSimpleName() + "\n");
    }
}
