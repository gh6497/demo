package cn.cat.springmvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cat
 * @Date: Created in 2018/1/23
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
@Controller
public class UploadController {
    @PostMapping("/upload") @ResponseBody
    // 此处的MultipartFile的参数名必须于表单中的name属性对应
    public Map<String, String> upload(@RequestParam("pic") MultipartFile pic) throws IOException {
        String name = pic.getName();

        System.out.println(name);
        String originalFilename = pic.getOriginalFilename();
        System.out.println(originalFilename);

        File file = new File("d:/test/" + originalFilename);
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }
        pic.transferTo(file);
        Map<String, String> map = new HashMap<>();
        map.put("msg", "上传成功");
        return map;
    }

}
