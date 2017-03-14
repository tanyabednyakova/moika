package io.khasang.moika.controller;

import io.khasang.moika.entity.SomeEntity;
import io.khasang.moika.entity.SomeSubEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SomeSubEntityIntegrationTest {
    private List<SomeSubEntity> getTestSubList() {
        SomeEntity someEntity = new SomeEntity();
        someEntity.setName("One");
        someEntity.setInterval(Duration.ofSeconds(50));
        List<SomeSubEntity> subEntities = new ArrayList<>();
        SomeSubEntity subEntity = new SomeSubEntity();
        subEntity.setName("Sub one");
        subEntity.setContent("111111111");
        subEntity.setSomeEntity(someEntity);
        subEntities.add(subEntity);
        subEntity = new SomeSubEntity();
        subEntity.setName("Sub two");
        subEntity.setContent("22222222");
        subEntity.setSomeEntity(someEntity);
        subEntities.add(subEntity);
        subEntity = new SomeSubEntity();
        subEntity.setName("Sub three");
        subEntity.setContent("3333333");
        subEntity.setSomeEntity(someEntity);
        subEntities.add(subEntity);
        subEntity = new SomeSubEntity();
        subEntity.setName("Sub four");
        subEntity.setContent("44444444");
        subEntity.setSomeEntity(someEntity);
        subEntities.add(subEntity);
        return subEntities;
    }

    @Test
    public void createSubEntities() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        SomeSubEntity resultEntity = null;
        for (SomeSubEntity subEntity : getTestSubList()) {
            if(resultEntity!=null){
                subEntity.setSomeEntity(resultEntity.getSomeEntity());
            }
            HttpEntity<SomeSubEntity> httpEntity = new HttpEntity<>(subEntity, httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            resultEntity = restTemplate.exchange(
                    "http://localhost:8080/some/sub/add",
                    HttpMethod.POST,
                    httpEntity,
                    SomeSubEntity.class
            ).getBody();
            Assert.assertNotNull(resultEntity);
        }
        RestTemplate restTemplate = new RestTemplate();
        List<SomeSubEntity> resultEntities = restTemplate.exchange(
                "http://localhost:8080/some/sub",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SomeSubEntity>>() {
                }
        ).getBody();
        Assert.assertNotNull(resultEntities);
        Assert.assertEquals(4, resultEntities.size());

        for (SomeSubEntity subEntity : resultEntities) {
            resultEntity = restTemplate.exchange(
                    "http://localhost:8080/some/sub/{id}",
                    HttpMethod.DELETE,
                    null,
                    SomeSubEntity.class,
                    subEntity.getId()
            ).getBody();
            Assert.assertNotNull(resultEntity);
        }
    }
}
