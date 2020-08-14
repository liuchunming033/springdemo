package com.lcm.jdk;

/**
 * 在切面中定义了两个增强的方法，分别为 myBefore() 方法和 myAfter() 方法，
 * 用于对目标类（CustomerDaoImpl）进行增强。
 */
public class MyAspect {
    public void myBefore() {
        System.out.println("方法执行之前");
    }

    public void myAfter() {
        System.out.println("方法执行之后");
    }
}
