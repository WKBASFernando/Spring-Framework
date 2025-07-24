package com.ijse.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Girl2 implements Agreement{
    @Override
    public void chat() {
        System.out.println("Girl ting2 chat");
    }
}
