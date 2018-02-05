package cn.cat.springmvc.demo.config;

import cn.cat.springmvc.demo.controller.MyExceptionHandler;
import cn.cat.springmvc.demo.converter.DateConverter;
import cn.cat.springmvc.demo.exception.GloaleExecptionHandler;
import cn.cat.springmvc.demo.intercepter.ErrorIntercepor;
import cn.cat.springmvc.demo.intercepter.MyInterceptor;
import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @Author: cat
 * @Date: Created in 2018/1/23
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */

@EnableWebMvc //启用mvc
@EnableCaching // 启用缓存注解
@ComponentScan("cn.cat.springmvc.demo")
public class WebConfig implements WebMvcConfigurer, EnvironmentAware {



    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }

    private Environment env;


    /**
     * ehcache管理器
     */
   /* @Bean
    public EhCacheCacheManager cacheManager(CacheManager cacheManager) {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(cacheManager);
        return ehCacheCacheManager;
    }*/

    /** private String name ;
     *  private int age;
     *  private gender;
     *  privat Integer id;
     *  private
     * ehcache管理器工厂bean
     *
     * @return
     */
   /* @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }*/

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        // 创建spring-reids默认实现的rediscache管理器
        return RedisCacheManager.create(connectionFactory);
    }


    /**
     * 配置文件上传解析器
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();

        return multipartResolver;
    }

    @Bean
    public Converter<String, Date> dateConverter() {
        return new DateConverter();
    }


    @Bean
    public HandlerInterceptor errorInterceptor() {
        return new ErrorIntercepor();
    }

    public HandlerExceptionResolver handlerExceptionResolver() {
        return new GloaleExecptionHandler();
    }

    /**
     * 配置静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**", "/js/**","/libs/**")
                .addResourceLocations("/css/", "/js/", "/libs/");
    }

    /**
     * 配置转换器
     *
     * @param
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(dateConverter());
    }

    /**
     * 配置视图解析器
     *
     * @param
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(errorInterceptor());
    }

    /**
     * 配置异步支持
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    /**
     * 配置异常处理
     *
     * @param resolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(handlerExceptionResolver());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toUpload").setViewName("upload");
        registry.addViewController("/login").setViewName("login");
    }


}
