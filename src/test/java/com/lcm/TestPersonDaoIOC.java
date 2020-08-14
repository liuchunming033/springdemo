package com.lcm;

import com.lcm.annotation.DogAction;
import com.lcm.dao.CustomerDao;
import com.lcm.dao.GoodsDao;
import com.lcm.gclib.MyGoodsBeanFactory;
import com.lcm.ioc.PersonDao;
import com.lcm.ioc.PersonService;
import com.lcm.jdk.MyBeanFactory;
import com.lcm.jdbc.service.SocService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPersonDaoIOC {
    /**
     * 测试控制反转http://c.biancheng.net/view/4251.html
     */
    private Logger log= LoggerFactory.getLogger(TestPersonDaoIOC.class);//括号里面放的是当前类的class文件。
    @Test
    public void testIOC() {
        String xmlPath = "META-INF/applicationContext.xml";
        //ClassPathXmlApplicationContext( ) 方法是在其所在的目录中寻找 .xml 配置文件,这里指的是编译后的 .class 文件所在的目录，不是 .java 文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        PersonDao personDao = (PersonDao) applicationContext.getBean("personDao");
        personDao.add();
        log.info("给spring添加log需要引入三个依赖：logback-classic、ogback-ext-spring和jcl-over-slf4j");
    }

    /**
     * 测试依赖注入http://c.biancheng.net/view/4253.html
     */
    @Test
    public void testDI() {
        String xmlPath = "META-INF/applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        PersonService personService = (PersonService) applicationContext.getBean("personService");
        personService.addPerson();
    }

    /**
     * 通过实现类 Person1 中默认的无参构造函数对 Bean 进行实例化。
     */
    @Test
    public void testBeanConstructor() {
        String xmlPath = "META-INF/applicationContext.xml"; //Spring 配置文件的路径，然后 Spring 容器会加载配置文件。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        //Spring 容器会通过实现类 Person1 中默认的无参构造函数对 Bean 进行实例化。
        System.out.println(applicationContext.getBean("person1")); // applicationContext.xml文件中的id=person1的Bean
    }

    @Test
    public void testBeanStaticFactory() {
        String xmlPath = "META-INF/applicationContext.xml"; //Spring 配置文件的路径，然后 Spring 容器会加载配置文件。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        System.out.println(applicationContext.getBean("person2")); // applicationContext.xml文件中的id=person1的Bean,这里的getBean是通过MyBeanFactory工厂进行实例化的
    }

    @Test
    public void testBeanFactory() {
        String xmlPath = "META-INF/applicationContext.xml"; //Spring 配置文件的路径，然后 Spring 容器会加载配置文件。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        System.out.println(applicationContext.getBean("person3")); // applicationContext.xml文件中的id=person1的Bean,这里的getBean是通过MyBeanFactory工厂进行实例化的
    }

    @Test
    public void testSingleton() {
        String xmlPath = "META-INF/applicationContext.xml"; //Spring 配置文件的路径，然后 Spring 容器会加载配置文件。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        System.out.println(applicationContext.getBean("person"));
        System.out.println(applicationContext.getBean("person")); //与上面一行得到的实例相同
    }

    @Test
    public void testProtoType() {
        String xmlPath = "META-INF/applicationContext.xml"; //Spring 配置文件的路径，然后 Spring 容器会加载配置文件。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        System.out.println(applicationContext.getBean("persons"));
        System.out.println(applicationContext.getBean("persons")); //得到一个新的实例
    }

    /**
     * http://c.biancheng.net/view/4264.html
     */
    @Test
    public void testInjection() {
        String xmlPath = "META-INF/applicationContext.xml"; //Spring 配置文件的路径，然后 Spring 容器会加载配置文件。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        System.out.println(applicationContext.getBean("personSetterInjection")); //利用设置值装配
        System.out.println(applicationContext.getBean("personConstructorInjection")); //利用构造方法装配
    }

    @Test
    public void testAnnotation() {
        String xmlPath = "META-INF/applicationContext.xml"; //Spring 配置文件的路径，然后 Spring 容器会加载配置文件。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        DogAction dogAction = (DogAction) applicationContext.getBean("dogAction");
        dogAction.add();
    }

    /**
     * Spring JDK动态代理
     * http://c.biancheng.net/view/4269.html
     */
    @Test
    public void testJDKProxy() {
        CustomerDao customerDao = MyBeanFactory.getBean();
        customerDao.add();
        customerDao.update();
        customerDao.delete();
        customerDao.find();
    }

    @Test
    public void testGCLIBproxy() {
        GoodsDao goodsDao = MyGoodsBeanFactory.getBean();
        goodsDao.add();
        goodsDao.delete();
        goodsDao.find();
        goodsDao.update();
    }

    /**
     * 测试通过Spring 的通知创建 AOP 代理。参考http://c.biancheng.net/view/4274.html
     * 需要在applicationContext.xml中的配置，首先配置目标类和通知。再配置代理，代理中配置4个属性
     * <!--目标类 -->
     * <bean id="customerDao" class="com.lcm.dao.CustomerDaoImpl" />
     * <!-- 通知 advice -->
     * <bean id="springAspect" class="com.lcm.factorybean.SpringAspect" />
     * <!--生成代理对象 -->
     * <bean id="customerDaoProxy" class="org.springframework.aop.framework.ProxyFactoryBean">
     * <!--代理实现的接口 -->
     * <property name="proxyInterfaces" value="com.lcm.dao.CustomerDao" />
     * <!--代理的目标对象 -->
     * <property name="target" ref="customerDao" />
     * <!--配置需要植入目标的通知 -->
     * <property name="interceptorNames" value="springAspect" />
     * <!-- 如何生成代理，true:使用cglib; false :使用jdk动态代理 -->
     * <property name="proxyTargetClass" value="true" />
     * </bean>
     */
    @Test
    public void testSpringProxy() {
        String xmlPath = "META-INF/applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        CustomerDao customerDao = (CustomerDao) applicationContext.getBean("customerDaoProxy");
        customerDao.find();
        customerDao.delete();
        customerDao.update();
        customerDao.add();
    }

    /**
     * 基于 XML 的声明式,所有的切面和通知都必须定义在 <aop:config> 元素中。
     * 新版本的 Spring 框架，建议使用 AspectJ 方式开发 AOP。
     * pom.xml中安装依赖aspectjrt和aspectjweaver
     * <!--目标类 -->
     * <bean id="customAspectJDao" class="com.lcm.dao.CustomerDaoImpl" />
     * <!--切面类 -->
     * <bean id="myAspectJ" class="com.lcm.aspectj.xml.AspectJ" />
     * <!--AOP 编程 -->
     * <aop:config>
     * <aop:aspect ref="myAspectJ">
     * <!-- 配置切入点，通知最后增强哪些方法,这里指的是增强com.lcm.dao 包下所有的方法。 -->
     * <aop:pointcut expression="execution ( * com.lcm.dao.*.* (..))" id="myPointCut" />
     * <!--前置通知，关联通知 Advice和切入点PointCut，method用来指定通知，pointcut-ref 属性用于指定切入点，也就是要增强的方法 -->
     * <aop:before method="myBefore" pointcut-ref="myPointCut" />
     * <!--后置通知，在方法返回之后执行，就可以获得返回值returning 属性 -->
     * <aop:after-returning method="myAfterReturning" pointcut-ref="myPointCut" returning="returnVal" />
     * <!--环绕通知 -->
     * <aop:around method="myAround" pointcut-ref="myPointCut" />
     * <!--抛出通知：用于处理程序发生异常，可以接收当前方法产生的异常 -->
     * <!-- *注意：如果程序没有异常，则不会执行增强 -->
     * <!-- * throwing属性：用于设置通知第二个参数的名称，类型Throwable -->
     * <aop:after-throwing method="myAfterThrowing" pointcut-ref="myPointCut" throwing="e" />
     * <!--最终通知：无论程序发生任何事情，都将执行 -->
     * <aop:after method="myAfter" pointcut-ref="myPointCut" />
     * </aop:aspect>
     * </aop:config>
     */
    @Test
    public void testAspectJ() {
        String xmlPath = "META-INF/applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        //// 从spring容器获取实例
        CustomerDao customerDao = (CustomerDao) applicationContext.getBean("customAspectJDao");
        customerDao.add();
    }

    /**
     * 基于 Annotation 方式实现 AOP 的效果是最方便的方式，所以实际开发中推荐使用注解的方式。
     * 【重要】在 com.lcm.dao.CustomerDaoImpl 目标类中添加注解 @Repository("customerDao")。
     * <!--扫描以下两个包下的所有注解-->
     * <context:component-scan base-package="com.lc.aspectj"/>
     * <context:component-scan base-package="com.lcm.dao"/>
     * <!-- 使切面开启自动代理 -->
     * <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
     */
    @Test
    public void testAnnotationAspectJ() {
        String xmlPath = "META-INF/applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        //// 从spring容器获取实例
        CustomerDao customerDao = (CustomerDao) applicationContext.getBean("customerDao");
        customerDao.add();
    }

    @Test
    public void testSoc() {
        // 获得Spring容器，并操作
        String xmlPath = "META-INF/applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        SocService accountService = (SocService) applicationContext.getBean("socService");
        accountService.transfer("00013e63048b441eb24cd8c787a16af1", 100);
    }
}
