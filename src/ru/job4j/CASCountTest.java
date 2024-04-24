package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class CASCountTest {
    @Test
    public void whenCasc() {
        CASCount casCount = new CASCount();
        casCount.increment();
        assertTrue("1", true);
        casCount.increment();
        assertTrue("2", true);
        casCount.increment();
        assertTrue("3", true);
        casCount.increment();
        assertFalse("9", false);
    }
}