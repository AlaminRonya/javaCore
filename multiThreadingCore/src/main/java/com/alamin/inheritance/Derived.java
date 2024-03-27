package com.alamin.inheritance;

public class Derived extends Base{
    private String value;
    public Derived(String value) {
        System.out.println("Derived::constructor");
        this.value = value;
    }
    public void check(){
        System.out.println("Derived::check-method");
        if (value.length() == 0){
            throw new RuntimeException("value is given empty!!");
        }
    }
    public static void print(){
        System.out.println("Derived::print-method");
    }
}
