package com.alamin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionExample {
    public static void main(String[] args) {
        //Type-Inference
        //List<Integer> numbers = new ArrayList<>(List.of(1,2,3));
        //ArrayList<Integer> numbers = new ArrayList<>(List.of(0,1,2,3));
        //var numbers = new ArrayList<>(List.of(1,2,3));
        // Collection removed
        //Collection<Integer> numbers = new ArrayList<>(List.of(1,2,3));
//        System.out.println(numbers);
//        numbers.remove(1);
//        System.out.println(numbers);

        // TODO: 28/03/2024 Arrays Question
        List<Integer> numbers = Arrays.asList(1,2,3);
        try {
            numbers.add(4);
        }catch (Exception e){
            System.out.println("add unsupported.");
        }
        try {
            numbers.set(2,2);
        }catch (Exception e){
            System.out.println("set unsupported.");
        }
        System.out.println(numbers);
    }
}
