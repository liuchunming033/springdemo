package com.lcm.helloworld;

public class HelloServiceImpl implements HelloService {
    private HelloDao helloDao;

    //  Spring 容器属性 setter 注入的方式，也是实际开发中较为常用的一种方式。
    public void setHelloDao(HelloDao helloDao) {
        this.helloDao = helloDao;
    }

    @Override
    public void sayHi() {
        helloDao.hello();
        helloDao.nihao();
    }

    @Override
    public void sayBye() {
        helloDao.bye();
        helloDao.zaijian();
    }
}
