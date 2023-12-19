package ru.personacode;
import ru.personacode.exceptions.DuplicateTabelNumber;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/*
Добавить метод, который ищет сотрудника по стажу (может быть список)
Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список) +
Добавить метод, который ищет сотрудника по табельному номеру +
Добавить метод добавление нового сотрудника в справочник +
 */

public class WorkerDB {
    private final HashMap<Integer, Worker> workerMap;

    public WorkerDB() {
        this.workerMap = new HashMap<>();
    }

    public void add (Worker worker) throws DuplicateTabelNumber {
        if (!workerMap.containsKey(worker.getTabNumber()))
            workerMap.put(worker.getTabNumber(), worker);
        else throw new DuplicateTabelNumber("Работник с таким табельным номером уже существует");
    }

    public List<Worker> getPhoneByName (String nameWorker) {
        return workerMap.values().stream().
                filter(s -> s.getNameWorker().equals(nameWorker)).
                collect(Collectors.toList());
    }

    public List<Worker> getWorkerByStageYear(int stageYears) {
        return workerMap.values().stream().
                filter(s -> s.getStageYear() == stageYears).
                collect(Collectors.toList());
    }

    public Worker getWorkerByTabNumber(int tabNumber) {
        return workerMap.get(tabNumber);
    }
}
