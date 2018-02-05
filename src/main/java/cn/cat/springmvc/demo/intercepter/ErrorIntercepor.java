package cn.cat.springmvc.demo.intercepter;

import cn.cat.springmvc.demo.exception.UserInfoException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: cat
 * @Date: Created in 2018/2/4
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
public class ErrorIntercepor implements HandlerInterceptor {
    /**
     * handler执行前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;
    }

    /**
     * handler执行后,并且没有异常时执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    /**
     * 无论如何都在handler执行后执行,可以在这里捕获异常
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*System.out.println("afterCompletion");
        System.out.println(handler);
        if (ex instanceof UserInfoException) {
            UserInfoException userInfoException = (UserInfoException) ex;
            Object o = userInfoException.getO();
            System.out.println("afterCompletion");
            System.out.println(o);
        }*/

    }
}
