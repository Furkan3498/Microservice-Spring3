package com.furkanceylan.studentservice.Controller;


import com.furkanceylan.studentservice.Repository.StudentRepository;
import com.furkanceylan.studentservice.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.addStudent(student);
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable("id")  Long id) {
        return studentRepository.findById(id);
    }

    @GetMapping
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/lesson/{lessonId}")
    public List<Student> findByLessonId(@PathVariable("lessonId") Long lessonId){
        return studentRepository.findByLessonId(lessonId);
    }
}
