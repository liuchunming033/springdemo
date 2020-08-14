package com.lcm.helloworld;

import org.springframework.stereotype.Repository;

@Repository("helloDao") //通过注解将数据访问层（DAO层）的类标识为 Spring 中的 Bean。相当于Spring配置文件applicationContext中定义bean的写法： <bean id="helloDao"class="com.lcm.ioc.HelloDaoImpl"/>
public class HelloDaoImpl implements HelloDao {
    @Override
    public void hello() {
        System.out.println("Hello world!");
    }

    @Override
    public void nihao() {
        System.out.println("你好，世界！");
    }

    @Override
    public void bye() {
        System.out.println("ByeBye！");
    }

    @Override
    public void zaijian() {
        System.out.println("再见！");
    }
}
