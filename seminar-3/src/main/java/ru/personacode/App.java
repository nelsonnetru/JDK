package ru.personacode;

import java.io.DataInputStream;
import java.util.Objects;

public class App {
    public static void main(String[] args) {
        MegaClass<String, DataInputStream, Float> mgClass = new MegaClass<>("Test",
                new DataInputStream(System.in), 5.2f);
        mgClass.printName();

        Number[] numArray = new Number[3];
        numArray[0] = 1;
        numArray[1] = 3.4f;
        numArray[2] = 4528940L;

        MyList<Number> myList = new MyList<>(numArray);
        myList.printList();
        myList.addElement(-9); myList.printList();
        myList.removeElement(1); myList.printList();

        MyList<Number> myList1 = new MyList<>();
        myList1.addElement(5.4f);
        myList1.addElement(12);
        myList1.addElement(3452354235L);
        myList1.printList();

        for (Number number : myList1) {
            System.out.println(number);
        }

        System.out.println(Calculator.sum(25f, 14));

        String[] arr1 = new String[]{"Winter", "Spring", "Summer", "Autumn"};
        String[] arr2 = new String[]{"Winter", "Spring", "Summer", "Autumn", "Winter"};
        System.out.println(compareArrays(arr1, arr2));

        Pair<String, Integer> pair = new Pair<>("Text", 100);
        System.out.println("FIRST: "+pair.getFirst()+", SECOND: "+pair.getSecond());
        System.out.println(pair);
    }

    public static <T> boolean compareArrays(T[] array1, T[] array2) {
        if (array1.length != array2.length) return false;
        for (int i = 0; i < array1.length; i++) {
            if (!Objects.equals(array1[i], array2[i])) return false;
        }
        return true;
    }
}
