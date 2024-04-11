package ru.job4j;

import java.io.*;
import java.util.function.Predicate;

public class SaveFile {
    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public String getContentWithoutPredicate(Predicate<Character> filter) {
        StringBuffer output = new StringBuffer();
        try (InputStream input = new FileInputStream(file)) {
            int data;
            while ((data = input.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append(data);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return output.toString();
    }

    public synchronized String getContentWithoutFilter() {
        return getContentWithoutPredicate(f -> true);
    }

    public synchronized String getContentWithoutUnicode() {
        Predicate<Character> filter = i -> i < 0x80;
        return getContentWithoutPredicate(filter);
    }
}
