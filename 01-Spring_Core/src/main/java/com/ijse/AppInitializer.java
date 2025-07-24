package com.ijse;

import com.ijse.bean.MyConnection;
import com.ijse.bean.SpringBean;
import com.ijse.bean.TestBean1;
import com.ijse.bean.TestBean2;
import com.ijse.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
/*

        SpringBean springBean1 = context.getBean(SpringBean.class);
        springBean1.test();
        SpringBean springBean2 = context.getBean(SpringBean.class);
        springBean2.test();
        System.out.println(springBean1);
        System.out.println(springBean2);

        //class name
        SpringBean springBean3 = context.getBean(SpringBean.class);
        System.out.println(springBean3);

        //BeanId
        SpringBean springBean4 = (SpringBean) context.getBean("SpringBean");
        System.out.println(springBean4);
*/


//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                context.close();
//            }
//        }); //yata thiyenne meke simple widiha

        TestBean1 bean1 = context.getBean(TestBean1.class);
        TestBean2 bean2 = context.getBean(TestBean2.class);
        System.out.println(bean1);
        System.out.println(bean2);

        MyConnection myConnection1 = context.getBean(MyConnection.class);
        MyConnection myConnection2 = context.getBean(MyConnection.class);
        System.out.println(myConnection1);
        System.out.println(myConnection2);

        context.registerShutdownHook();

        context.close();
    }
}