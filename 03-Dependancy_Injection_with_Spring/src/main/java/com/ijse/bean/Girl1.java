package com.ijse.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Girl1 implements Agreement{


    public void chat() {
        System.out.println("Girl 1 chatting");
    }

    public Girl1() {
        System.out.println("Girl Constructor Called");
    }
}
