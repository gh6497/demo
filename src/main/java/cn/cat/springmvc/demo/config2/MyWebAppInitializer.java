package cn.cat.springmvc.demo.config2;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 暂时不知道是配置什么的
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /**
     * 实现这个方法注册整个应用程序的配置
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
    /*@Override
    protected Filter[] getServletFilters() {
        // 配置spring乱码过滤器
        return new Filter[]{new CharacterEncodingFilter("utf-8")};
    }*/

    /**
     * 重写这个方法配置是否支持异步 (默认支持)
     * @return
     */
    @Override
    protected boolean isAsyncSupported() {
        return super.isAsyncSupported();
    }
}