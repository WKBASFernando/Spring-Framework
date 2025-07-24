package com.ijse.config;

import com.ijse.bean.A;
import com.ijse.bean.B;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@Import({AppConfig.class})
@ComponentScan(basePackages = "com.ijse.bean")
public class AppConfig1 {
    @Bean
    public A a () {
        return new A();
    }

    @Bean
    public B b () {
        return new B();
    }
}
