package com.qcq.spring_boot_demo;

import com.qcq.spring_boot_demo.bean.Husband;
import com.qcq.spring_boot_demo.bean.MyContext;
import com.qcq.spring_boot_demo.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

//可以在测试期间自动注入
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {
    @Autowired
    Person person;
    @Autowired
    Husband husband;
    @Autowired
    ApplicationContext context;
    @Autowired
    MyContext myContext;

    @Test
    public void contextLoads() {
        System.out.println(person);
        System.out.println(husband);


    }

    @Test
    public void testHelloService() {
        System.out.println(context.containsBean("helloService"));

    }

    @Test
    public void sayHelloByMyContext() {
        myContext.sayHello();
    }

    @Test
    public void testLogger(){
        //日志记录器
        Logger logger= LoggerFactory.getLogger(getClass());
        //日志级别，由低到高
        //可以调整输出的日志级别
        //springBoot默认只输出info及以上的级别
        logger.trace("这是trace日志");
        logger.debug("这是debug日志");
        logger.info("这是info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");
    }

}
