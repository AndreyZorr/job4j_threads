package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {


    private int size;
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (this.queue.size() >= this.size) {
                wait();
            }
            notifyAll();
        }
        this.queue.add(value);
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (this.queue.size() == 0) {
                wait();
            }
            notifyAll();
        }
        T qPoll = this.queue.poll();
        return qPoll;
    }
}