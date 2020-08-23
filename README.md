# inaction
inaction -- project for practice


1、pom的管理
    pom依赖的Jar包版本号统一用dependencyManagement来声明。
2、启动器继承SpringBootServletInitializer类并重写configure方法，可以使用外部tomcat启动。
3、日志 log4j2/logback
    （1）使用logback：参考https://www.cnblogs.com/bigdataZJ/p/springboot-log.html。
        Spring Boot默认使用LogBack日志系统，如果要使用LogBack，原则上是需要添加spring-boot-starter-logging的dependency依赖的
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        但是因为新建的Spring Boot项目一般都会引用spring-boot-starter或者spring-boot-starter-web，而这两个起步依赖中都已经包含了对于spring-boot-starter-logging的依赖，所以，无需额外添加依赖。    
    （2）使用log4j2：参考https://www.cnblogs.com/bigdataZJ/p/spring-boot-log4j2.html。
        先添加jar包依赖，再在resources目录下添加配置文件log4j2.xml;
        jar包依赖需要添加log4j的log4j-slf4j-impl(缺少会报Failed to load class "org.slf4j.impl.StaticLoggerBinder"错误)和slf4j-api；
        
4、如果不想每次都写private final static Logger logger = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j。该注解是Lombok插件中的，需要在pom文件加入lombok的依赖。
5、