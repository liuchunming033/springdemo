package com.lcm.instance;

/**
 * 静态工厂用于创建 Bean 的实例
 */
public class MyBeanStaticFactory {

    public static Person2 createBean() {
        return new Person2();
    }
}
