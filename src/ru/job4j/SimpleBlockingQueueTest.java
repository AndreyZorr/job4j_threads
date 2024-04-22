package ru.job4j;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleBlockingQueueTest {
    @Test
    void whenPoll() throws InterruptedException {
        SimpleBlockingQueue simpleBlockingQueue = new SimpleBlockingQueue(10);
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 50; i++) {
                        try {
                            simpleBlockingQueue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "thread1"
        );

        Thread thread2 = new Thread(
                () -> {
                    for (int i = 0; i < 40; i++) {
                        try {
                            simpleBlockingQueue.poll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "thread2"
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(simpleBlockingQueue.poll()).isEqualTo(40);
    }

    @Test
    void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread produser = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 6; i++) {
                            queue.offer(i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }, "Produser"
        );
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "Consumer"
        );
        produser.start();
        consumer.start();
        produser.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer).containsExactly(0, 1, 2, 3, 4, 5);
    }
}