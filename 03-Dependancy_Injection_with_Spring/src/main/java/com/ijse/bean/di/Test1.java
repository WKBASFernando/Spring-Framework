package com.ijse.bean.di;

import org.springframework.stereotype.Component;

@Component
public class Test1 implements DI{
    private Test1() {
        System.out.println("Test1 Constructor Called");
    }

    @Override
    public void sayHello() {
        System.out.println("Test1 SayHello Called");
    }
}
