package com.furkanceylan.lessonservice.config;

import com.furkanceylan.lessonservice.client.StudentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    //BURADA LB AYARLARINI YAPTIK. EĞER FİLTER VERMEZSEK

    @Bean
    public WebClient studentWebClient(){
        return WebClient.builder().baseUrl("http://student-service").filter(filterFunction).build();
    }

    @Bean
    public StudentClient studentClient(){
        HttpServiceProxyFactory httpServiceProxyFactory =HttpServiceProxyFactory.builderFor(WebClientAdapter.create(studentWebClient())).build();
        return httpServiceProxyFactory.createClient(StudentClient.class);
    }

    //STUDENT CLİENTI DÖNEBİLMEMİZ İÇİN WEB CLİENTTAN STUDENT SERVİSE BİR BAĞLANTI SAĞLADIK. ORADAN YARATTIĞIMIZ WEB CLİENTI DA HTPPSERVİCEPROXYFACTORYE ATTIK
    //ORADAN DA CLİENT YARATARAK GERİ DÖNÜŞ SAĞLADIK
}
