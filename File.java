package com.bank.system.Files;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class File {

    public static ArrayList<String> LoadData(String Path) {
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void SaveData(String path, ArrayList<String> data) {
        try {
            Files.deleteIfExists(Paths.get(path));

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                for (String line : data) {
                    writer.write(line);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}