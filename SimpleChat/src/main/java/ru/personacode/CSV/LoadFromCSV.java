package ru.personacode.CSV;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadFromCSV {
    private final String path;

    public LoadFromCSV(String path) {
        this.path = path;
    }

    public ArrayList<String> load() {
        ArrayList<String> resultArrayList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                resultArrayList.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + path + " не найден, история сообщений пуста.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return resultArrayList;
    }
}
