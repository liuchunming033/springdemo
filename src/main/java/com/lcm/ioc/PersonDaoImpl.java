package com.lcm.ioc;

import org.springframework.stereotype.Repository;

/**
 * 首先使用 @Repository 注解将 PersonDaoImpl 类标识为 Spring 中的 Bean
 * 其写法相当于配置文件中定义bean的写法： <bean id="personDao"class="com.lcm.ioc.PersonDaoImpl"/>
 * 使用注解的方式更普遍
 */
@Repository("personDao") //用于将数据访问层（DAO层）的类标识为 Spring 中的 Bean，
public class PersonDaoImpl implements PersonDao{
    @Override
    public void add() {
        System.out.println("save()执行了...");
    }
}
