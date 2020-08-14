package com;

import com.lcm.ioc.PersonDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPersonDaoIOC {
    public static void main(String[] args) {
        // TODO: 演示控制反转， applicationContext.xml——>ClassPathXmlApplicationContext——>Bean对象
        String xmlPath = "META-INF/applicationContext.xml"; //ClassPathXmlApplicationContext( ) 方法在resourc root目录中寻找 .xml 配置文件，因此要先相对于resources root写xml文件路径
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath); //从类路径 ClassPath 中寻找指定的 XML 配置文件，找到并装载完成 ApplicationContext 的实例化工作
        PersonDao personDao = (PersonDao) applicationContext.getBean("personDao");  //IOC容器ApplicationContext帮我们创建对象。
        personDao.add();
    }
}
