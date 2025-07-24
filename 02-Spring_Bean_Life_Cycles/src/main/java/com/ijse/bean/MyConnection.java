package com.ijse.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyConnection implements BeanNameAware, BeanFactoryAware, InitializingBean, ApplicationContextAware, DisposableBean{
    //State of instantiation
    public MyConnection() {
        System.out.println("MyConnection Constructor Called");
    }
    // there is no method to find state of populated properties

    //Bean name aware-> bean ID
    @Override
    public void setBeanName(String name) {
        System.out.println("MyConnection setBeanName Called");
    }

    // Add dependencies to the bean
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("SetBeanFactory Called");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("AfterPropertiesSet Called");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("MyApplicationContext Called");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("MyConnection is Destroyed");
    }
}
