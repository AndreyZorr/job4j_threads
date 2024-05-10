package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


public class Search<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T object;
    private final int from;
    private final int to;

    public Search(T[] array, T object, int from, int to) {
        this.array = array;
        this.object = object;
        this.from = from;
        this.to = to;
    }

    private int findIndex() {
        for (int i = from; i < to; i++) {
            if (object.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return findIndex();
        }
        int middle = (from + to) / 2;
        Search<T> leftSearch = new Search<>(array, object, from, middle);
        Search<T> rightSearch = new Search<>(array, object, middle + 1, to);
        leftSearch.fork();
        rightSearch.fork();
        return Math.max(leftSearch.join(), rightSearch.join());
    }

    public static <T> int findIndex(T[] array, T search) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new Search<>(array, search, 0, array.length));
    }
}
