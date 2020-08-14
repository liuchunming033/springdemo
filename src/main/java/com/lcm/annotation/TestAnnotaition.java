package com.lcm.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotaition {

    public static void main(String[] args) {
        String xmlPath = "META-INF/annotation-applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        DogAction dogAction = (DogAction) applicationContext.getBean("dogAction");
        dogAction.add();
    }
}
