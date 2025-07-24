package com.ijse.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class SpringBean {
    @Autowired
    public SpringBean(@Value("Angelo Fernando") String name, @Value("2") int number, @Value("true") boolean value) {
        System.out.println(name);
        System.out.println(number);
        System.out.println(value);
    }

    public SpringBean(@Value("Angelo Fernando") String name, @Value("2") int number) {
        System.out.println(name);
        System.out.println(number);
    }


}
