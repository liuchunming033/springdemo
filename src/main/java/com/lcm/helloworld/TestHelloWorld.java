package com.lcm.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHelloWorld {
    public static void main(String[] args) {
        //ClassPathXmlApplicationContext( ) 方法在resourc root目录中寻找 .xml 配置文件，因此要先相对于resources root写xml文件路径
        String xmlPath = "META-INF/helloworld-applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        //IOC容器ApplicationContext帮我们创建对象helloDao。
        HelloDao helloDao = (HelloDao) applicationContext.getBean("helloDao");
        helloDao.hello();
        helloDao.bye();
    }
}
