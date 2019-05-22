package com.btyer.model;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：角色实体类
 *
 * @author btyer
 * @create 2019/3/17 7:52
 */
public class AyRole implements Serializable {
    private Integer id;
    private String name;

    private List<AyUser> ayUserList;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AyUser> getAyUserList() {
        return ayUserList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAyUserList(List<AyUser> ayUserList) {
        this.ayUserList = ayUserList;
    }

    @Override
    public String toString() {
        return "AyRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ayUserList=" + ayUserList +
                '}';
    }
}
