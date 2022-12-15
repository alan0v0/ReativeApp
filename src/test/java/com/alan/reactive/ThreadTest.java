package com.alan.reactive;

import org.junit.jupiter.api.Test;

import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
    @Test
    void park() throws InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " interrupted");
            return 100;
        });
        Thread anotherThread = new Thread(task);
        anotherThread.wait();
        anotherThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("elapsed:2 sec");
        anotherThread.interrupt();
        anotherThread.join();
    }

    @Test
    void defaultWaitStatus() throws InterruptedException {
        Lock lock = new ReentrantLock(true);
        lock.lock();
        Thread another = new Thread(() -> {
            lock.lock();
            System.out.println("get");
            lock.unlock();
        });
        another.start();
        Thread.sleep(10000000);
        lock.unlock();
        another.join();
    }
}
