package com.lcm.instance;

/**
 * 实例工厂方式实例化Bean
 */
public class MyBeanFactory {
    public MyBeanFactory() {
        System.out.println("person3工厂实例化中");
    }

    public Person3 createBean() {
        return new Person3();
    }
}
