package com.alamin.atomic_operation;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicStackDemo {
    public static void main(String[] args) {
        AtomicStack<AtomicInteger> atomicStack = new AtomicStack<>();
        atomicStack.push(new AtomicInteger(0));
        atomicStack.push(new AtomicInteger(1));
        atomicStack.push(new AtomicInteger(2));
        var writeThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicStack.push(new AtomicInteger(0));
                atomicStack.push(new AtomicInteger(1));
                atomicStack.push(new AtomicInteger(2));
            }
        });
        var writeThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicStack.push(new AtomicInteger(0));
                atomicStack.push(new AtomicInteger(1));
                atomicStack.push(new AtomicInteger(2));
            }
        });
        var readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (atomicStack.getTop().get().next != null){
                    System.out.println(atomicStack.pop());
                }

            }
        });
        writeThread1.start();
        writeThread2.start();
        readThread.start();
        try {
            writeThread1.join();
            writeThread2.join();
            readThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
