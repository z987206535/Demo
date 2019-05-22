package com.btyer.model;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：
 *
 * @author btyer
 * @create 2019/3/11 9:42
 */
public class AySchool implements Serializable {

    private Integer id;
    private String name;
    //一对多
    private List<AyStudent> students;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AyStudent> getStudents() {
        return students;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<AyStudent> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "AySchool{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
