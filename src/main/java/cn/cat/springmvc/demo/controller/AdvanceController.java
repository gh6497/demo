package cn.cat.springmvc.demo.controller;

import cn.cat.springmvc.demo.exception.UserInfoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cat
 * @Date: Created in 2018/2/4
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
@ControllerAdvice
public class AdvanceController {
    /**
     * 可以在的这里处理异常
     *  异常捕获的顺序是 ContollerAavice
     *  HandlerIntercptor
     *  HandlerException
     *  这里是最佳的捕获异常的地方，
     *  这里可以捕获数据绑定异常一直设定响应的状态吗
     * @param e
     * @return
     */
    /*@ExceptionHandler(UserInfoException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handlerException(UserInfoException e) {
        System.out.println("handlerException");
        Object o = e.getO();
        Map<String, Object> map = new HashMap<>(1);
        map.put("response", o);
        return map;
    }*/
}
