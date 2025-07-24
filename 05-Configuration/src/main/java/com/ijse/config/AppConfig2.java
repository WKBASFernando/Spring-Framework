package com.ijse.config;

import com.ijse.bean.C;
import com.ijse.bean.D;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan (basePackages = "com.ijse.bean")
//@Import({AppConfig.class})
public class AppConfig2 {
    @Bean
    public C c () {
        return new C();
    }

    @Bean
    public D d () {
        return new D();
    }
}
