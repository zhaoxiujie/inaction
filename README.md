# inaction
inaction -- project for practice


1、pom的管理：依赖的Jar包版本号统一用dependencyManagement来声明。

2、启动器继承SpringBootServletInitializer类并重写configure方法，可以使用外部tomcat启动。

3、日志 log4j2/logback
（1）使用logback：参考https://www.cnblogs.com/bigdataZJ/p/springboot-log.html
    Spring Boot默认使用LogBack日志系统，如果要使用LogBack，原则上是需要添加spring-boot-starter-logging的dependency依赖的 org.springframework.boot spring-boot-starter-logging。
    但是因为新建的Spring Boot项目一般都会引用spring-boot-starter或者spring-boot-starter-web，而这两个起步依赖中都已经包含了对于spring-boot-starter-logging的依赖，所以，无需额外添加依赖。
 
（2）使用log4j2：参考https://www.cnblogs.com/bigdataZJ/p/spring-boot-log4j2.html
    将springboot的spring-boot-starter-web包中的spring-boot-starter-logging排掉，引入spring-boot-starter-log4j2包，再在application.property文件中指定logging.config。
   （可能会出现报Failed to load class "org.slf4j.impl.StaticLoggerBinder"错误，是因为需要 桥接Slf4j to Log4j2 的包，比如log4j-slf4j-impl或slf4j-log4j12等，而项目没有引入；spring-boot-starter-log4j2中就包含log4j-slf4j-impl)；

4、如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j。该注解是Lombok插件中的，需要在pom文件加入lombok的依赖。

5、分支init为框架的初始完整结构，整合了logback,但是inaction-common等其他module中还没有任何内容。

6、 单点登录的jar包sso-uim-spring不能将commons-lang排掉，不然配置的'excludePath'会报错。

7、