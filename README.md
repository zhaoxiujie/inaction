# inaction
inaction -- project for practice


1、pom的管理
    pom依赖的Jar包版本号统一用dependencyManagement来声明。
2、启动器继承SpringBootServletInitializer类并重写configure方法，可以使用外部tomcat启动。
3、log4j2
4、如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j。该注解是Lombok插件中的，需要在pom文件加入lombok的依赖。