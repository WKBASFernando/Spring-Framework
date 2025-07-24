package com.ijse.bean.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test2 {
    @Autowired
    DI di;

    public Test2() {
        System.out.println("Test2 Constructor Called");
    }

    public void test(){
        di.sayHello();
    }
}
