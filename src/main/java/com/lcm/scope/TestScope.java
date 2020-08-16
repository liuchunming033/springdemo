package com.lcm.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class TestScope {

    public static void main(String[] args) {
        testSingleton();
        testProtoType();
    }

    /**
     * 单例模式，使用 singleton 定义的 Bean 在 Spring 容器中只有一个实例，这也是 Bean 默认的作用域。
     */
    public static void testSingleton() {
        String xmlPath = "META-INF/scope-applicationContext.xml"; //Spring 配置文件的路径，然后 Spring 容器会加载配置文件。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        log.info(applicationContext.getBean("person").toString());
        log.info(applicationContext.getBean("person").toString());//与上面一行得到的实例相同
    }

    /**
     * 原型模式，每次通过 Spring 容器获取 prototype 定义的 Bean 时，容器都将创建一个新的 Bean 实例。
     */
    public static void testProtoType() {
        String xmlPath = "META-INF/scope-applicationContext.xml"; //Spring 配置文件的路径，然后 Spring 容器会加载配置文件。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        log.info(applicationContext.getBean("persons").toString());
        log.info(applicationContext.getBean("persons").toString());//得到一个新的实例
    }
}
