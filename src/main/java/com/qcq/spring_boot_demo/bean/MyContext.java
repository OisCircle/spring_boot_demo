package com.qcq.spring_boot_demo.bean;

import com.qcq.spring_boot_demo.service.impl.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyContext {
    @Autowired
    ApplicationContext context;

    public void sayHello(){
    	HelloService service=(HelloService) context.getBean("helloService");
        System.out.println(service.sayHello());
    }

}
