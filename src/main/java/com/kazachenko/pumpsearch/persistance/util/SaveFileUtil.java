package com.kazachenko.pumpsearch.persistance.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;

public class SaveFileUtil {

    public static <T> void save(Collection<T> modelsList, String filePath, Class<T> clazz) {
        try (FileOutputStream fileOutputStream = getFileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = getObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(modelsList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static FileOutputStream getFileOutputStream(String filePath) throws FileNotFoundException {
        return new FileOutputStream(filePath);
    }

    private static ObjectOutputStream getObjectOutputStream(FileOutputStream fileOutputStream) throws IOException {
        return new ObjectOutputStream(fileOutputStream);
    }
}
