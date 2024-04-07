package com.furkanceylan.lessonservice.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.furkanceylan.lessonservice.Model.Lesson;
import com.furkanceylan.lessonservice.Repository.LessonRepository;
import com.furkanceylan.lessonservice.client.StudentClient;
import com.furkanceylan.lessonservice.producer.LessonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {


    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private StudentClient studentClient;
    @Autowired
    private LessonProducer lessonProducer;


    @PostMapping
    public Lesson addLesson(@RequestBody Lesson lesson) {
        return lessonRepository.addLesson(lesson);
    }

    @GetMapping("/{id}")
    public Lesson getLessonById(@PathVariable Long id) {
        return lessonRepository.findById(id);
    }

    @GetMapping
    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }


    @GetMapping("/with-students")
    public List<Lesson> findAllWithStudents() {
        List<Lesson> lessonList = lessonRepository.findAll();

        lessonList.forEach(f -> f.setStudentList(studentClient.findByLessonId(f.id)));
        return lessonList;
    }

    @PostMapping("/notification")
    public ResponseEntity<Lesson>  postLessonEvent(@RequestBody Lesson lesson) throws JsonProcessingException {
        //kafka producer

        lessonProducer.sendLessonTopic(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(lesson);
    }



}
