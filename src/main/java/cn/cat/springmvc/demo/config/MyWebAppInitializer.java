package cn.cat.springmvc.demo.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 继承{@AbstractAnnotationConfigDispatcherServletInitializer}来配置springmvc
 * 这个类是{@WebApplicationInitializer}的实现类.继承这个类可以极大的简化对springmvc的配置
 *
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 这个Class对象注解的配置会加载到spring容器中，
     * shiro相关的配置只能加载在spring的容器中，如果加载在springmvc的容器中，
     * 会报找不到bean的错误，因为访问不到springmvc容器的东西
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                DaoConfig.class, ShiroConfig.class
        };
    }

    /**
     * 这个Class对象数组中的配置会加载到springmvc的容器，所以这里只能用来配置
     * springmvc及dispatcher控制器的相关配置，配置别的可能会出错
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class
        };
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
        // spring代理shiro的过滤器
        DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy("shiroFilter");
        shiroFilter.setTargetFilterLifecycle(true);
        return new Filter[]{new CharacterEncodingFilter("utf-8"),
                shiroFilter
        };
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