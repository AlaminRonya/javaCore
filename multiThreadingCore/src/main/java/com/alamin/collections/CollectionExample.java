package com.alamin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionExample {
    public static void main(String[] args) {
        //List<Integer> numbers = new ArrayList<>(List.of(1,2,3));
        ArrayList<Integer> numbers = new ArrayList<>(List.of(0,1,2,3));
        System.out.println(numbers);
        numbers.remove(1);
        System.out.println(numbers);
    }
}
