package org.example.config;

/**
 * applicationContext.xml:
 * 		<context:component-scan base-package="org.example.config"/>
 * 		<util:properties id="jdbcProperties" location="classpath:org/example/config/jdbc.properties"/>
 */
@Configuration
//@Import({oneConfig.class, twoConfig.class})
public class AppConfig {
	
    private @Value("#{jdbcProperties.url}") String jdbcUrl;
    private @Value("#{jdbcProperties.username}") String username;
    private @Value("#{jdbcProperties.password}") String password;
    
    /**
     * @Bean(name = "myFooS")
     * @Bean(initMethod = "init")
     * @Bean(destroyMethod = "cleanup")
     * @Scope("prototype")
     * @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
     */
    @Bean
    public FooService fooService() {
    	FooService fooService = new FooServiceImpl();
    	fooService.setFooRepository(fooRepository());
        return fooService;
    }
    @Bean
    public FooRepository fooRepository() {
    	FooRepository fooRepository = new FooReopsitoryImpl();
    	fooRepository.setSessionFactory(sessionFactory());
        return fooRepository;
    }
    @Bean
    public SessionFactory sessionFactory() {
        AnnotationSessionFactoryBean asFactoryBean = new AnnotationSessionFactoryBean();
        asFactoryBean.setDataSource(dataSource());
        return asFactoryBean.getObject();
    }
    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(jdbcUrl, username, password);
    }
}

/**
 * applicationContext.xml:
 * 		<context:annotation-config/>
 * 		<context:component-scan base-package="org.example"/>
 */
/**
 * @Controller 	用于控制层 myAction
 * @Service 	用于业务层 myServiceImpl
 * @Repository 	用于持久层 myDaoImpl
 * @Component	都可用	例：@Component("userAction")/@Component("userManager")/@Component("userDao")
 * @Service("myService")
 */
@Scope("prototype")
@Service
public class MyServiceImpl implements MyService {
	
	/**
	 * 自动注入 byType 
	 * 在找不到或找到多个匹配 Bean 时Spring 容器将抛出 BeanCreationException 异常
	 */
	@Autowired
	private MyDao1 myDao1;
	
	/**
	 * 自动注入 byType （一般仅开发或测试期使用）
	 * 在找不到匹配 Bean 时也不报错
	 */
	@Autowired(required = false)
	private MyDao2 myDao2;
	
	/**
	 * 自动注入 byName 
	 * 起别名,避免找到多个匹配的 Bean ,抛 BeanCreationException 异常
	 */
	@Autowired
	@Qualifier("myDao3")
	private MyDao3 myDao3;
	
	/**
	 * JSR-250规范定义的注解(common-annotations.jar)
	 * 自动注入 byName（推荐使用）
	 * 等同于myDao3
	 * 如果不指定 name 属性,则默认等于属性名 myDao4
	 */
	@Resource(name = "myDao4")
	private MyDao4 myDao4;
	
	/**
	 * 自动注入
	 * 等同于 myDao1
	 */
	@Bean
	private MyDao5 myDao5;
	
	/**
	 * 自动注入
	 * 起别名,设置 scope = "singleton" , 设置 lazy-init = "true"
	 */
	@Bean
	@Qualifier("myDao6")
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	@Lazy
	private MyDao6 myDao6;
	
	/**
	 * 自动注入
	 * 设置 scope = "session" ,...
	 */
	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	private MyDao7 myDao7;
	
	/**
	 * 自动注入
	 * 使用EL设置默认值
	 */
	@Bean
	@Value("#{privateInstance.age}")
	private String myDao8;
	
	
	/**
	 * JSR-250规范定义的注解
	 * 在初始化的时候调用
	 */
	@PostConstruct
	public void init() {
		//TODO
	}
	
	/**
	 * JSR-250规范定义的注解
	 * 在结束的时候调用
	 */
	@PreDestroy
	public void destroy() {
		//TODO
	}
	
	//TODO getX() setX()
}

/**
 * applicationContext.xml:
 * 		<tx:annotation-driven transaction-manager="mytxManager"/>
 */
@Transactional
public class MyServiceImpl implements MyService {
	
	@Transactional(readOnly = true, rollbackFor = , rollbackForClassname = , noRollbackFor = , noRollbackForClassname = )
	public Foo findFoo(String fooName) {...}

	public void updateFoo(Foo foo) {...}
}


/**
 * SR 330标准的Annotations
 * <dependency>
 *   <groupId>javax.inject</groupId>
 *   <artifactId>javax.inject</artifactId>
 *   <version>1</version>
 * </dependency>
 * @Inject = @Autowired
 * @Named = @Component
 * @Singleton = @Scope("singleton")
 * @Named = @Qualifier
 */


