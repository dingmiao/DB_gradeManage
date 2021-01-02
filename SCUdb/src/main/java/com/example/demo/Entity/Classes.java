package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Classes {
    public Classes() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Classes(Integer id, String department, String class_name, Integer teacher_id, String term, String time) {
        Id = id;
        this.department = department;
        this.class_name = class_name;
        this.teacher_id = teacher_id;
        this.term = term;
        this.time = time;
    }

    @Id
    private Integer Id;
    private String department;
    private String class_name;
    private Integer teacher_id;
    private String term;
    private String time;
}
