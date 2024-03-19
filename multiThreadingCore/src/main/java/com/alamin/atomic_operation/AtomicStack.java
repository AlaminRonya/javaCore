package com.alamin.atomic_operation;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicStack<T> {

    private AtomicReference<Node<T>> top;

    public AtomicStack() {
        this.top = new AtomicReference<>();
    }

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        while (true) {
            Node<T> currentNode = top.get();
            //To-do: Some processing based on the value read
            newNode.next = currentNode;
            if (top.compareAndSet(currentNode, newNode)) {
                //Success
                break;
            }
        }
    }

    public T pop() {
        while (true) {
            Node<T> currentTop = top.get();
            if (currentTop == null) {
                return null;
            }
            Node<T> newTop = currentTop.next;
            if (top.compareAndSet(currentTop, newTop)) {
                return currentTop.value;
            }
        }
    }

    public AtomicReference<Node<T>> getTop() {
        return top;
    }
}