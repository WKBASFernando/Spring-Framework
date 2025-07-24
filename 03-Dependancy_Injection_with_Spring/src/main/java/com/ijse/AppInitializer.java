package com.ijse;

import com.ijse.bean.Boy;
import com.ijse.bean.di.Test2;
import com.ijse.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        Test2 test2 = context.getBean(Test2.class);
        test2.test();

        context.registerShutdownHook();
    }
}