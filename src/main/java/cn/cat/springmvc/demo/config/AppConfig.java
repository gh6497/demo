package cn.cat.springmvc.demo.config;

import cn.cat.springmvc.demo.controller.MyExceptionHandler;
import cn.cat.springmvc.demo.converter.DateConverter;
import cn.cat.springmvc.demo.intercepter.MyInterceptor;
import com.alibaba.druid.pool.DruidDataSource;
import net.sf.ehcache.CacheManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

/**
 * @Author: cat
 * @Date: Created in 2018/1/23
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
@EnableWebSecurity // 启用spring安全管理
@EnableWebMvc //启用mvc
@EnableCaching // 启用缓存注解
@Configuration //相当一个beans标签里的内容
@ComponentScan("cn.cat.springmvc.demo")
@EnableTransactionManagement //注解事务管理器
@PropertySource("classpath:properties/db.properties") // 加载配置文件
public class AppConfig implements WebMvcConfigurer, EnvironmentAware {

    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }

    private Environment env;

    /**
     * 通用mapper扫描的配置
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("cn.cat.springmvc.demo.mapper");
        return scannerConfigurer;
    }
    /**
     * ehcache管理器
     */
    @Bean
    public EhCacheCacheManager cacheManager(CacheManager cacheManager) {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(cacheManager);
        return ehCacheCacheManager;
    }

    /**
     *  ehcache管理器工厂bean
     * @return
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }
    // 配置 druid数据源
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        System.out.println("env:" + env);
        String url = env.getProperty("jdbc.driver");
        dataSource.setDriverClassName(url);
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        return dataSource;
    }

    // 配置mybatis
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/SqlMapConfig.xml"));
        return sqlSessionFactoryBean;
    }
    // 配置事物管理器
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
         return dataSourceTransactionManager;
    }
    /**
     * jedis连接工厂
     * spring提供了3中配置jedis连接工厂的方式
     * {@link RedisStandaloneConfiguration} 单机版的配置
     * {@link RedisClusterConfiguration} 集群版的配置
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        //
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);

        return jedisConnectionFactory;
    }
    /**
     * 配置文件上传解析器
     *
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();

        return multipartResolver;
    }
    @Bean
    public Converter<String,Date> dateConverter() {
        return new DateConverter();
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        return new StringRedisTemplate(jedisConnectionFactory());
    }

    @Bean
    public HandlerInterceptor handlerInterceptor() {
        return new MyInterceptor();
    }

    public HandlerExceptionResolver handlerExceptionResolver() {
        return new MyExceptionHandler();
    }
    /**
     * 配置静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**", "/js/**")
                .addResourceLocations("/css/", "/js/");
    }

    /**
     * 配置转换器
     * @param
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(dateConverter());
    }

    /**
     * 配置视图解析器
     * @param
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor());
    }

    /**
     * 配置异步支持
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    /**
     * 配置异常处理
     * @param resolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
//        resolvers.add(handlerExceptionResolver());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toUpload").setViewName("upload");
    }



}
