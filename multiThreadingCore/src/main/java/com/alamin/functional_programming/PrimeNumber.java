package com.alamin.functional_programming;

import java.util.stream.IntStream;

public class PrimeNumber {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(i +" is prime number: "+isPrime(i));
        }
    }
    private static boolean isPrime(final int number){
        return number > 1 && IntStream.range(2, (number/2)+1)
                .noneMatch(index -> number % index == 0);
    }
}
