package cn.cat.springmvc.demo.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 继承{@AbstractAnnotationConfigDispatcherServletInitializer}来配置springmvc
 * 这个类是{@WebApplicationInitializer}的实现类.继承这个类可以极大的简化对springmvc的配置
 *
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 与web没有什么关系的配置类，如数据库的事务，缓存，消息队列等
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                DaoConfig.class,JmsConfig.class
        };
    }

    /**
     * servlet 相关的配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /**
     * 实现这个方法配置springmvc前端控制器的拦截路径
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 重写这个配置过滤器
     * 从这里配置的filter都会对springmvc前端控制器进行拦截,不需要配置拦截路径
     * 也在这里也不能配置拦截路径,哈哈,因为我们用的springmvc而不是servlet
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        // 配置spring乱码过滤器
        return new Filter[]{new CharacterEncodingFilter("utf-8")};
    }

    /**
     * 重写这个方法配置是否支持异步 (默认支持)
     * @return
     */
    @Override
    protected boolean isAsyncSupported() {
        return super.isAsyncSupported();
    }
}