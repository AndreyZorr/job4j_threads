package ru.job4j.pool.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RolColSumTest {

    @Test
    public void whenSumMatrix() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] expected = RolColSum.sum(matrix);
        assertEquals(expected[0].getRowSum(), 6);
        assertEquals(expected[0].getColSum(), 12);
    }

    @Test
    public void whenAsyncSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] expected = RolColSum.asyncSum(matrix);
        assertEquals(expected[0].getRowSum(), 6);
        assertEquals(expected[0].getColSum(), 12);
    }
}
