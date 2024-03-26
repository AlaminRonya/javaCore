package com.alamin.inheritance;

public class Derive extends Base{
    private String value;
    public Derive(String value) {
        System.out.println("Derive::constructor");
        this.value = value;
    }
    public void check(){
        System.out.println("Derive::check-method");
        if (value.length() == 0){
            throw new RuntimeException("value is given empty!!");
        }
    }
}
