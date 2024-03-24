package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }

    @Override
    public void run() {
        var process = new char[]{'-', '\\', '|', '/' };
        int c = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
            Thread.sleep(500);
            System.out.print("\r load: " + process[c++]);
            if (c == process.length) {
                    c = 0;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
