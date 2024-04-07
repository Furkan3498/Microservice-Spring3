package com.furkanceylan.lessonservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.furkanceylan.lessonservice.Model.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class LessonProducer {
    @Autowired
    KafkaTemplate<Long, String> kafkaTemplate;
    @Autowired
    ObjectMapper objectMapper;  //We have to convert java to JSON

    public void sendLessonTopic(Lesson lesson) throws JsonProcessingException {
        Long id = lesson.getId();

        String value = objectMapper.writeValueAsString(lesson);


        CompletableFuture<SendResult<Long, String>> completableFuture = kafkaTemplate.sendDefault(id, value);

        completableFuture.thenApply(result->{
            handleSucces(id,value,result);
            return result;

        });

        completableFuture.exceptionally(throwable -> {
            handlerFailure(throwable);
            return null;
        });
    }
    private void handleSucces(Long id, String value, SendResult<Long ,String> result){
        System.out.println("Mesaj Gönderildi! Key : " +id +"value : " + value + "partition : " + result.getRecordMetadata().partition());
    }

    private void handlerFailure(Throwable throwable){
        System.out.println("Mesaj gönderilirken hata alındı!" + throwable.getMessage());

        try {
            throw  throwable ;
        } catch (Throwable throwablei) {
            System.out.println("Error in OnFailure : " + throwablei.getMessage());
        }
    }


}
