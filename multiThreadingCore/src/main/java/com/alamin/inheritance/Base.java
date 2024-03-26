package com.alamin.inheritance;

public class Base {
    public Base() {
        System.out.println("Base::constructor");
        check();
    }

    public void check(){
        System.out.println("Base::check-method");
    }
}
