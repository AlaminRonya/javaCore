package com.alamin.atomic_operation;

public class Node<T> {
    T value;
    Node<T> next;

    Node(T value) {
        this.value = value;
    }
}