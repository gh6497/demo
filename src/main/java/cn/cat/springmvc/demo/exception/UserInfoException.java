package cn.cat.springmvc.demo.exception;

/**
 * @Author: cat
 * @Date: Created in 2018/2/4
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
public class UserInfoException extends RuntimeException {
    public String msg;
    public Object o;
    public UserInfoException(String msg,Object o) {
        super(msg);
        this.o = o;
        this.msg = msg;
    }

    public Object getO() {
        return o;
    }
}
