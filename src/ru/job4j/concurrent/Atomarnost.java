package ru.job4j.concurrent;

public class Atomarnost {
    private int value;

    public void increment() {
        value++;
    }

    public int get() {
        return value;
    }
}
