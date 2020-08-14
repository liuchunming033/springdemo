package com.lcm.jdk;

import com.lcm.dao.CustomerDao;
import com.lcm.dao.CustomerDaoImpl;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * 补充知识，匿名类https://time.geekbang.org/course/detail/100027801-102596?utm_source=pinpaizhuanqu&utm_medium=geektime&utm_campaign=guanwang&utm_content=0511&utm_term=guanwang
 */
public class MyBeanFactory {
    //定义一个静态方法
    public static CustomerDao getBean() {
        //// 准备目标类
        final CustomerDao customerDao = new CustomerDaoImpl();
        //创建切面类
        final MyAspect myAspect = new MyAspect();
        // 使用代理类，进行增强。第一个参数是当前类的类加载器，第二个参数是所创建实例的实现类对应的接口，第三个参数就是需要增强的方法
        return (CustomerDao) Proxy.newProxyInstance(
                MyBeanFactory.class.getClassLoader(),
                new Class[]{CustomerDao.class}, //.class得到一个CustomerDao的Class实例
                new InvocationHandler() { //匿名类实现InvocationHandler这个接口
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        myAspect.myBefore();
                        Object obj = method.invoke(customerDao, args);
                        myAspect.myAfter();
                        return obj;
                    }
                });
    }
}
