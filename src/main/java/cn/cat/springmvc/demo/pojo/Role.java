package cn.cat.springmvc.demo.pojo;

/**
 * @Author: cat
 * @Date: Created in 2018/1/26
 * @PersonHome: http://blog.csdn.net/csdn6497
 * @Description:
 */
public class Role {
    private Integer id;

    private String name;

    private Byte status;

    private String abbr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
}
