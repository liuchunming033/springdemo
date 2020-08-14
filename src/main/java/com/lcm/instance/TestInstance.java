package com.lcm.instance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestInstance {
    private static Logger log= LoggerFactory.getLogger(TestInstance.class);//括号里面放的是当前类的class文件。

    public static void main(String[] args) {
        testBeanConstructor();
        testBeanStaticFactory();
        testBeanFactory();
    }

    /**
     * Spring 容器会通过实现类 Person1 中默认的无参构造函数对 Bean 进行实例化。
     */
    public static void testBeanConstructor() {
        String xmlPath = "META-INF/instance-applicationContext.xml"; //Spring 配置文件的路径，然后 Spring 容器会加载配置文件。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        log.info(applicationContext.getBean("person1").toString());
    }

    /**
     * 这里的getBean是通过MyBeanStaticFactory静态工厂进行实例化的，因为在ApplicationContext配置了factory-method
     * <bean id="person2" class="com.lcm.instance.MyBeanStaticFactory" factory-method="createBean"/>
     */
    public static void testBeanStaticFactory() {
        String xmlPath = "META-INF/instance-applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        log.info(applicationContext.getBean("person2").toString());
    }

    /**
     * 这里的getBean是通过MyBeanFactory实例工厂进行实例化的，因为在ApplicationContext配置了factory-bean和factory-method
     *     <bean id="person3" factory-bean="myBeanFactory" factory-method="createBean"/>
     */
    public static void testBeanFactory() {
        String xmlPath = "META-INF/instance-applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        System.out.println(applicationContext.getBean("person3"));
    }
}
