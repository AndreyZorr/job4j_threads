package ru.job4j.pool.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;

class RolColSumTest {

    @Test
    public void whenSumMatrix() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] result = RolColSum.sum(matrix);
        Sums[] expected = {
                new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18)
        };
        assertArrayEquals(expected, result);

    }

    @Test
    public void whenAsyncSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] result = RolColSum.asyncSum(matrix);
        Sums[] expected = {
                new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18)
        };
        assertArrayEquals(expected, result);
    }
}
