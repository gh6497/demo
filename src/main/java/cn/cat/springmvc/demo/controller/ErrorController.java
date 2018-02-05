package cn.cat.springmvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: cat
 * @Date: Created in 2018/2/4
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
@Controller
@RequestMapping("/error/{code}")
public class ErrorController {
    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml() {

        return null;
    }
    @RequestMapping
    @ResponseBody
    public Object error() {
        return null;
    }

}
