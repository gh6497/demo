package cn.cat.springmvc.demo.pojo;

/**
 * @Author: cat
 * @Date: Created in 2018/1/24
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
public class Student {
    private Integer studentId;

    private String name;

    private Integer age;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
