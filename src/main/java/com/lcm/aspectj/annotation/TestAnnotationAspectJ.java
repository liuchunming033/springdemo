package com.lcm.aspectj.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于 Annotation 方式实现 AOP 的效果是最方便的方式，所以实际开发中推荐使用注解的方式。
 * 【重要】在 com.lcm.dao.CustomerDaoImpl 目标类中添加注解 @Repository("customerDao")。
 * <!--扫描以下两个包下的所有注解-->
 * <context:component-scan base-package="com.lc.aspectj.annotation"/>
 * <!-- 使切面开启自动代理 -->
 * <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
 */


public class TestAnnotationAspectJ {
    public static void main(String[] args) {
        String xmlPath = "META-INF/aop-applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        //// 从spring容器获取实例
        StatusDao statusDao = (StatusDao) applicationContext.getBean("statusDao");
        statusDao.add();
        statusDao.update();//异常通知
    }
}
