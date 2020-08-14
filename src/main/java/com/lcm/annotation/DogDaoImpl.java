package com.lcm.annotation;

import org.springframework.stereotype.Repository;


/**
 * 使用 @Repository 注解将 数据访问层（DAO层）的类DogDaoImpl 标识为 Spring 中的 Bean.
 * 其写法相当于配置文件中 <bean id="dogDao"class="com.lcm.annotation.DogDaoImpl"/> 的书写。
 */
@Repository("dogDao") //给这个bean起一个标识，叫dogDao
public class DogDaoImpl implements DogDao {
    @Override
    public void add() {
        System.out.println("Dao层的add方法执行了");
    }
}
