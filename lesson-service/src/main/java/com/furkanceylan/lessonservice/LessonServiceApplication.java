package com.furkanceylan.lessonservice;

import com.furkanceylan.lessonservice.Model.Lesson;
import com.furkanceylan.lessonservice.Repository.LessonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LessonServiceApplication implements CommandLineRunner {

	private final LessonRepository lessonRepository;

	public LessonServiceApplication(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(LessonServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Lesson l1 = Lesson.builder()
				.id(Long.valueOf(10))
				.name("Math")
				.build();
		lessonRepository.addLesson(l1);
	}
}
