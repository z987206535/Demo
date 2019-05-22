package com.btyer.model;

import java.io.Serializable;

/**
 * 描述：
 *
 * @author btyer
 * @create 2019/3/10 14:01
 */
public class AyUserAddress implements Serializable {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AyUserAddress{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
