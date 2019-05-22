package com.btyer.model;

import java.io.Serializable;

/**
 * 描述：
 *
 * @author btyer
 * @create 2019/3/11 9:41
 */
public class AyStudent implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private AySchool aySchool;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public AySchool getAySchool() {
        return aySchool;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAySchool(AySchool aySchool) {
        this.aySchool = aySchool;
    }

    @Override
    public String toString() {
        return "AyStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", aySchool=" + aySchool +
                '}';
    }
}
