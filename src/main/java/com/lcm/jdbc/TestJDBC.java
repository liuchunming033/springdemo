package com.lcm.jdbc;

import com.lcm.jdbc.service.SocService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJDBC {
    public static void main(String[] args) {
            // 获得Spring容器，并操作
            String xmlPath = "META-INF/jdbc-applicationContext.xml";
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
            SocService socService = (SocService) applicationContext.getBean("socService");
            socService.transfer("00013e63048b441eb24cd8c787a16af1", 100);
    }
}
