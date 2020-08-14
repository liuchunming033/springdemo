package com.lcm.gclib;

import com.lcm.dao.GoodsDao;
import com.lcm.dao.MyAspect;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyGoodsBeanFactory {
    public static GoodsDao getBean() {
        //准备目标类
        final GoodsDao goodsDao = new GoodsDao();
        //准备切面类对象
        final MyAspect myAspect = new MyAspect();
        //生成代理类，GCLIB在运行时，生成指定的对象的子类，增强
        Enhancer enhancer = new Enhancer();
        //确定需要增强的类
        enhancer.setSuperclass(goodsDao.getClass());
        //添加回调函数
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                myAspect.myBefore();
                Object obj = method.invoke(goodsDao,objects);
                myAspect.myAfter();
                return obj;

            }
        });
        GoodsDao goodsDaoProxy = (GoodsDao) enhancer.create();
        return goodsDaoProxy;
    }
}
