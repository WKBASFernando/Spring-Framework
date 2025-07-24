package com.ijse.config;

import com.ijse.bean.MyConnection;
import com.ijse.bean.TestBean1;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "com.ijse.bean")  //class names widihata palleha select karala thiyenne
//@ComponentScan(basePackageClasses = TestBean1.class)
public class AppConfig {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public MyConnection getMyConnection() {
        return new MyConnection();
    }
}
