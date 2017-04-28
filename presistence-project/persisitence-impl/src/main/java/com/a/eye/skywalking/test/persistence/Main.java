package com.a.eye.skywalking.test.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xin on 2016/12/14.
 */
public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        try {
            context.start();
            Thread.currentThread().join();
        } catch (Exception e) {
            logger.error("Failed to start persistence service.", e);
            System.exit(-1);
        }
    }
}
