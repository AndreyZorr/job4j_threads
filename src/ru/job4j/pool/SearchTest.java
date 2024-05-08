package ru.job4j.pool;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    @Test
    public void whenDifferentDataTypesThen() {
        String[] strings = {"A", "B", "C", "D"};
        int ind = Search.findIndex(strings, "B");
        assertEquals(1, ind);
    }

    @Test
    public void whenLinearSearch() {
        Integer[] integers = {1, 2, 3, 4};
        int ind = Search.findIndex(integers, 2);
        assertEquals(1, ind);
    }

    @Test
    public void whenParallelSearch() {
        Integer[] integers = new Integer[50];
        for (int i = 0; i < 50; i++) {
            integers[i] = i + 1;
        }
        int ind = Search.findIndex(integers, 20);
        assertEquals(19, ind);
    }

    @Test
    public void whenNotFound() {
        Integer[] integers = {10, 20, 30, 40, 50};
        int ind = Search.findIndex(integers, 55);
        assertEquals(-1, ind);
    }
}