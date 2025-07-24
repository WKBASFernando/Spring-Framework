package com.ijse;

import com.ijse.bean.SpringBeanOne;
import com.ijse.bean.SpringBeanTwo;
import com.ijse.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        SpringBeanOne springBeanOne1 = context.getBean(SpringBeanOne.class);
        SpringBeanOne springBeanOne2 = context.getBean(SpringBeanOne.class);
        System.out.println(springBeanOne1);
        System.out.println(springBeanOne2);

        SpringBeanTwo springBeanTwo1 = context.getBean(SpringBeanTwo.class);
        SpringBeanTwo springBeanTwo2 = context.getBean(SpringBeanTwo.class);
        System.out.println(springBeanTwo1);
        System.out.println(springBeanTwo2);

        context.registerShutdownHook();
    }
}