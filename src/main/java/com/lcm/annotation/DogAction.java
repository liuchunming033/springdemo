package com.lcm.annotation;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;


/**
 *@Controller 通常作用在控制层（如 Struts2 的 Action），用于将控制层的类标识为 Spring 中的 Bean，其功能与 @Component 相同。
 */
@Controller("dogAction")  //起个标识叫dogAction，其写法相当于在配置文件中编写 <bean id="dogAction"class="com.lcm.annotation.DogAction"/>。
public class DogAction {
    //对 Bean 的属性变量、属性的 Set 方法及构造函数进行标注，配合对应的注解处理器完成 Bean 的自动配置工作。
    @Resource(name = "dogService") //这相当于在配置文件内编写 <property name="dogService"ref="dogService"/>。
    private DogService dogService;

//    public DogService getDogService() {
//        return dogService;
//    }

    public void add() {
        dogService.add();
        System.out.println("Action层的add()方法执行了...");
    }
}
