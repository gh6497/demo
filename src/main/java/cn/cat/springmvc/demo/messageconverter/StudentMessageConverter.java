package cn.cat.springmvc.demo.messageconverter;

import cn.cat.springmvc.demo.pojo.Student;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author: cat
 * @Date: Created in 2018/1/24
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
public class StudentMessageConverter extends AbstractHttpMessageConverter<Student> {
    public StudentMessageConverter() {
        super(Charset.forName("utf-8"), MediaType.APPLICATION_FORM_URLENCODED);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz == Student.class;
    }

    @Override
    protected Student readInternal(Class<? extends Student> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {

        return null;
    }

    @Override
    protected void writeInternal(Student student, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

    }
}
