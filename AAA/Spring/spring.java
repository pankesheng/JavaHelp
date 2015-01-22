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
 * @Controller 	���ڿ��Ʋ� myAction
 * @Service 	����ҵ��� myServiceImpl
 * @Repository 	���ڳ־ò� myDaoImpl
 * @Component	������	����@Component("userAction")/@Component("userManager")/@Component("userDao")
 * @Service("myService")
 */
@Scope("prototype")
@Service
public class MyServiceImpl implements MyService {
	
	/**
	 * �Զ�ע�� byType 
	 * ���Ҳ������ҵ����ƥ�� Bean ʱSpring �������׳� BeanCreationException �쳣
	 */
	@Autowired
	private MyDao1 myDao1;
	
	/**
	 * �Զ�ע�� byType ��һ��������������ʹ�ã�
	 * ���Ҳ���ƥ�� Bean ʱҲ������
	 */
	@Autowired(required = false)
	private MyDao2 myDao2;
	
	/**
	 * �Զ�ע�� byName 
	 * �����,�����ҵ����ƥ��� Bean ,�� BeanCreationException �쳣
	 */
	@Autowired
	@Qualifier("myDao3")
	private MyDao3 myDao3;
	
	/**
	 * JSR-250�淶�����ע��(common-annotations.jar)
	 * �Զ�ע�� byName���Ƽ�ʹ�ã�
	 * ��ͬ��myDao3
	 * �����ָ�� name ����,��Ĭ�ϵ��������� myDao4
	 */
	@Resource(name = "myDao4")
	private MyDao4 myDao4;
	
	/**
	 * �Զ�ע��
	 * ��ͬ�� myDao1
	 */
	@Bean
	private MyDao5 myDao5;
	
	/**
	 * �Զ�ע��
	 * �����,���� scope = "singleton" , ���� lazy-init = "true"
	 */
	@Bean
	@Qualifier("myDao6")
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	@Lazy
	private MyDao6 myDao6;
	
	/**
	 * �Զ�ע��
	 * ���� scope = "session" ,...
	 */
	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	private MyDao7 myDao7;
	
	/**
	 * �Զ�ע��
	 * ʹ��EL����Ĭ��ֵ
	 */
	@Bean
	@Value("#{privateInstance.age}")
	private String myDao8;
	
	
	/**
	 * JSR-250�淶�����ע��
	 * �ڳ�ʼ����ʱ�����
	 */
	@PostConstruct
	public void init() {
		//TODO
	}
	
	/**
	 * JSR-250�淶�����ע��
	 * �ڽ�����ʱ�����
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
 * SR 330��׼��Annotations
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


