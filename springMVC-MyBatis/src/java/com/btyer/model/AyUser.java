package com.btyer.model;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：用户实体类
 *
 * @author btyer
 * @create 2019/3/10 13:59
 */
public class AyUser implements Serializable {

    private Integer id;
    private String name;
    private String password;
    private Integer age;
   // private AyUserAddress ayUserAddress;

    private List<AyRole> ayRoleList;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

/*    public AyUserAddress getAyUserAddress() {
        return ayUserAddress;
    }*/

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

 /*   public void setAyUserAddress(AyUserAddress ayUserAddress) {

        this.ayUserAddress = ayUserAddress;
    }*/

    public void setAyRoleList(List<AyRole> ayRoleList) {
        this.ayRoleList = ayRoleList;
    }

    public List<AyRole> getAyRoleList() {
        return ayRoleList;
    }

    @Override
    public String toString() {
        return "AyUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", ayRoleList=" + ayRoleList +
                '}';
    }
}
