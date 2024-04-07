package com.furkanceylan.lessonservice.client;

import com.furkanceylan.lessonservice.Model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface StudentClient {

    @GetExchange("/student/lesson/{lessonId}")
    public List<Student> findByLessonId(@PathVariable("lessonId") Long lessonId);

    // bu interface Student kendisi gidiyor bizim gidip uğraşmamızı önlüyor.
}
