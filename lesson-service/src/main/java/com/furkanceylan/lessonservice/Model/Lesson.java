package com.furkanceylan.lessonservice.Model;

import lombok.Builder;

import java.util.List;


@Builder
public class Lesson {

    public Long id;
    public String name;
    public List<Student> studentList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
