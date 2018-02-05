package cn.cat.springmvc.demo.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: cat
 * @Date: Created in 2018/2/4
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
public class GloaleExecptionHandler implements HandlerExceptionResolver {
    /**
     * 这里可以捕获所有的异常
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("resolveException");
        System.out.println(handler);
        if (ex instanceof UserInfoException) {
            UserInfoException userInfoException = (UserInfoException) ex;
            Object o = userInfoException.getO();
            System.out.println("afterCompletion");
            System.out.println(o);
        }
        return null;
    }
}
