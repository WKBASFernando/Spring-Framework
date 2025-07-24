package com.ijse.config;

import com.ijse.bean.SpringBeanOne;
import com.ijse.bean.SpringBeanTwo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan (basePackages = "com.ijse.bean")
@Import({AppConfig1.class, AppConfig2.class})
public class AppConfig {
    @Bean
    public SpringBeanOne springBeanOne() {
        return new SpringBeanOne();
    }

    @Bean
    public SpringBeanTwo springBeanTwo() {
        return new SpringBeanTwo();
    }
}
