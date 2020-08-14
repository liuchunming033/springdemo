package com.lcm.ioc;

/**
 * 这个类通过属性 setter 注入的案例演示 Spring 容器是如何实现依赖注入的。
 * 首先声明了 personDao 对象，并为其添加 setter 方法，用于依赖注入
 * setter 注入的方式，也是实际开发中较为常用的一种方式。
 * 然后实现了 PersonDao 接口的 addPerson() 方法，并在方法中调用 save() 方法和输出一条语句。
 */
public class PersonServiceImpl implements PersonService{
    // PersonServiceImpl依赖PersonDao接口，这里定义接口声明，而不是实现类
    private PersonDao personDao;  //不需要手动private PersonDao personDao=new PersonDaoImpl();

    // 提供set()方法，用于依赖注入，被调用者的实例不再由调用者创建，而是由 Spring 容器创建，这称为控制反转。
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void addPerson() {
        personDao.add();
        System.out.println("addPerson()执行了...");
    }
}
