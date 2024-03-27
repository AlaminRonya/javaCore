package com.alamin.inheritance;

public class Main {
    public static void main(String[] args) {
//        try{
//            new Derived("");
//        }catch (Exception e){
//            System.out.println(e);
//        }
        Base base = new Derived("");
        base.print();
    }
}
