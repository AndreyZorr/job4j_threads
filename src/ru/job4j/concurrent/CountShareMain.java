package ru.job4j.concurrent;

public class CountShareMain {
    public static void main(String[] args) throws InterruptedException {
        Atomarnost atomarnost = new Atomarnost();
        Thread first = new Thread(atomarnost::increment);
        Thread second = new Thread(atomarnost::increment);
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(atomarnost.get());
    }
}
