package ru.job4j.pool;

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
        RolColSum.Sums[] result = RolColSum.sum(matrix);
        assertEquals(6, result[0].getRowSum());
        assertEquals(12, result[0].getColSum());
    }

    @Test
    public void whenAsyncSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] result = RolColSum.asyncSum(matrix);
        assertEquals(6, result[0].getRowSum());
        assertEquals(12, result[0].getColSum());
    }
}
