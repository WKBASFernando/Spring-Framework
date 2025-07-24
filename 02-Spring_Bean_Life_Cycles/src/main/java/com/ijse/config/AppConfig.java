package com.ijse.config;

import com.ijse.bean.MyConnection;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan (basePackages = "com.ijse.bean")
public class AppConfig {
    @Bean

    public MyConnection getMyConnection() {
        return new MyConnection();
    }
}