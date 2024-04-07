package com.furkanceylan.studentservice.Repository;

import com.furkanceylan.studentservice.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    public List<Student> studentList = new ArrayList<>();

    public Student addStudent(Student student) {
        studentList.add(student);
        return student;
    }

    public Student findById(Long id) {

        return studentList.stream().filter(c -> c.id().equals(id)).findFirst().orElseThrow();
    }

    public List<Student> findAll(){
        return studentList;
    }

    public List<Student> findByLessonId(Long id){

        return studentList.stream().filter(d -> d.lessonId().equals(id)).toList();
    }
}
