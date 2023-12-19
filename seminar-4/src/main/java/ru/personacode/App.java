package ru.personacode;

import ru.personacode.exceptions.DuplicateTabelNumber;

import java.util.Calendar;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        WorkerDB workers = new WorkerDB();
        try {
            workers.add(new Worker(1, "79095556644", "Иван",
                    new Date(2000-1900, Calendar.JANUARY, 1)));
            workers.add(new Worker(2, "79105502365", "Дмитрий",
                    new Date(2022-1900, Calendar.MARCH, 15)));
            workers.add(new Worker(3, "79163335966", "Алексей",
                    new Date(2013-1900, Calendar.AUGUST, 12)));
            workers.add(new Worker(4, "79153107845", "Елена",
                    new Date(2023-1900, Calendar.DECEMBER, 18)));
            workers.add(new Worker(5, "79150007845", "Елена",
                    new Date(2020-1900, Calendar.SEPTEMBER, 20)));

        } catch (DuplicateTabelNumber e) {
            e.printStackTrace();
        }

        System.out.println(workers.getWorkerByTabNumber(2));
        System.out.println(workers.getPhoneByName("Елена"));
        System.out.println(workers.getWorkerByStageYear(23));
    }
}
