package ru.personacode.CSV;

import ru.personacode.Message;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToCSV {
    private String path = "log.csv";

    public void save(Message message) {
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.append(message.generateToChatArea());
            writer.append("\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Файл " + path + " не найден и будет создан!");
        }
    }

    public String getPath() {
        return path;
    }
}
