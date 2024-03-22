package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                }
        );
        Thread second = new Thread(
                () -> {}
        );
        System.out.println(first.getState() + " First");
        System.out.println(second.getState() + " Second");
        first.start();
        second.start();
       if (first.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState());
        } else if (second.getState() != Thread.State.TERMINATED) {
           System.out.println(second.getState());
       }
        System.out.println("Работа завершена");
    }
}
