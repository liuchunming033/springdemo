
在main下新建文件夹resources用于存放资源文件, 在resources上右键，选择mark directory as 选中Resources Root

# 1. 安装Spring依赖
<!--添加Spring框架，系统会自动的下载所需的Spring依赖包spring-core,spring-expression,spring-aop以及spring-beans-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.2.7.RELEASE</version>
        </dependency>
# 2. 添加日志依赖并配置
在spring-context中排除对commons-logging的依赖
<!--排除对 commons-logging 的依赖-->
            <exclusions>
                <exclusion>
                    <artifactId>commons-logging</artifactId>
                    <groupId>commons-logging</groupId>
                </exclusion>
            </exclusions>
            
添加三个依赖包：
<!-- 日志框架使用slf4j+logback+spring -->
        <dependency>
            <!--logback-classic(其中包含logback-core,slf4j-api,不需要额外再配置logback-core和slf4j-api)-->
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.3</version>
        </dependency>
        <dependency>
            <!--logback-ext-spring(logback集成到spring中需要该jar包，spring中配置logback监听LogbackConfigListener就是用到该jar包)-->
            <groupId>org.logback-extensions</groupId>
            <artifactId>logback-ext-spring</artifactId>
            <version>0.1.5</version>
        </dependency>
        <dependency>
            <!--jcl-over-slf4j(java-common-logging框架和slf4j的桥接jar包，有了这个jar包，spring框架中通过jcl记录的日志信息就可以输出到自己用slf4j记录的日志文件中了)-->
            <groupId>org.slf4j</groupId>
            <artifactId>jcl-over-slf4j</artifactId>
            <version>1.7.25</version>
        </dependency>
配置日志格式logback.xml放到resources资源目录下。
# 3. 第一个Spring项目

代码：package com.lcm.helloworld;
# 4. 实例化Bean的三种方式

代码：package com.lcm.instance;

# 5. Spring中Bean的作用域
Spring 中 bean 的 5 种作用域，然后详细介绍 singleton 和 prototype 这两种最常用的作用域。

代码：package com.lcm.scope;

# 6. Beand的生命周期
当一个 Bean 被加载到 Spring 容器时，它就具有了生命，通过实现特定的接口或 <bean> 的属性设置，都可以对 Bean 的生命周期过程产生影响。

http://c.biancheng.net/view/4261.html

# 7. 使用Annotation装配Bean
Spring 的 Bean 的装配方式，如基于 XML 的 Bean 装配、基于 Annotation 的 Bean 装配和自动装配等。最常用的是Annotaition。

创建Dao、Service和Controller，分别用@Repository、@Service和@Controller装饰实现类，在引用上层的资源时使用@Resource引用资源

代码：package com.lcm.annotation;

# 8. 基于 Annotation 的声明式AOP

代码：package com.lcm.aspectj.annotation;

# 9. JDBCTemplate
作为 Spring JDBC 的核心，JdbcTemplate 类中包含了所有数据库操作的基本方法。包括添加、修改、查询和删除等操作。
1. JDBC 的相关信息是在 Spring 配置文件中完成的

定义了三个 Bean，分别是 dataSource、jdbcTemplate 和需要注入类的 Bean。其中 dataSource 对应的是 DriverManagerDataSource 类，用于对数据源进行配置；jdbcTemplate 对应 JdbcTemplate 类，该类中定义了 JdbcTemplate 的相关配置。

2. Spring 的事务处理位于业务逻辑层，它提供了针对事务的解决方案。

Spring 的事务属性分别为传播行为、隔离级别、只读和超时属性，这些属性提供了事务应用的方法和描述策略。

传播行为可以控制是否需要创建事务以及如何创建事务。

http://c.biancheng.net/view/4282.html

代码：package com.lcm.jdbc;
