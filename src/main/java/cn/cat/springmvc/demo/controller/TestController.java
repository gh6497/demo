package cn.cat.springmvc.demo.controller;

import cn.cat.springmvc.demo.config1.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cat
 * @Date: Created in 2018/1/23
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
@Controller
public class TestController {
    @RequestMapping("/")
    public String index() {



        return "index";
    }
    @RequestMapping("/student") @ResponseBody
    public Student testRuqest(@RequestBody Student student) {
        return null;
    }
}
