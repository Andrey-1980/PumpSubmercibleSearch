package com.kazachenko.pumpsearch.persistance.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LoadFileUtil {

    public static <T> List<T> loadAll(String filePath, Class<T> clazz) {

        List<T> modelList = new ArrayList<>();
        try (FileInputStream fis = getFileInputStream(filePath);
             ObjectInputStream objectInputStream = getObjectInputStream(fis)) {

            return (List<T>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return modelList;
    }

    private static FileInputStream getFileInputStream(String filePath) throws FileNotFoundException {
        return new FileInputStream(filePath);
    }

    private static ObjectInputStream getObjectInputStream(FileInputStream fileInputStream) throws IOException {
        return new ObjectInputStream(fileInputStream);
    }
}
