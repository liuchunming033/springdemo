package com.lcm.annotation;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Service 通常作用在业务层（Service 层），用于将业务层的类标识为 Spring 中的 Bean，其功能与 @Component 相同。
 * 其写法相当于配置文件中 <bean id="dogService"class="com.lcm.annotation.DogServiceImpl"/> 的书写。
 */
@Service("dogService") //将 DogServiceImpl 类标识为 Spring 中的 Bean，这样就可以在使用这个类的里面注入它了
public class DogServiceImpl implements DogService {
    //@Resource 中有两个重要属性：name 和 type。Spring 将 name 属性解析为 Bean 实例名称，type 属性解析为 Bean 实例类型。
    @Resource(name = "dogDao") //相当于配置文件中 <property name="dogDao" ref="dogDao"/> 的写法，依赖注入了DogDaoImpl类型的实例
    private DogDao dogDao; // Spring框架帮我们实例化DogDao

//    public DogDao getDogDao() {
//        return dogDao;
//    }

    @Override
    public void add() {
        dogDao.add(); //调用dogDao中的add()方法
        System.out.println("Service层的add()方法执行了...");
    }
}
