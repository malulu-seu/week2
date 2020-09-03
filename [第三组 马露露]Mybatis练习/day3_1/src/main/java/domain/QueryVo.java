package domain;

import java.io.Serializable;

/**
 * 查询类，用于组合多条件查询
 * 将多个条件组合成一个查询类
 */

public class QueryVo implements Serializable {

    private String name;

    private String address;

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
