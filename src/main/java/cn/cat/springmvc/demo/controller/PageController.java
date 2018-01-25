package cn.cat.springmvc.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: cat
 * @Date: Created in 2018/1/23
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
@Controller
public class PageController  {
    @RequestMapping("/page/{type}")
    public String page(@PathVariable("type") String type) {

        return   type;
    }
}
