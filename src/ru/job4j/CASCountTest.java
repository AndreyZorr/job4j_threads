package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class CASCountTest {
    @Test
    public void whenCasc() throws InterruptedException {
        final CASCount casCount = new CASCount();
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        casCount.increment();
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        casCount.increment();
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertEquals(casCount.get(), 200);
    }
}
