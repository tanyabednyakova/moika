package io.khasang.moika.controller;

import io.khasang.moika.entity.SomeEntity;
import io.khasang.moika.entity.SomeSubEntity;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class SomeEntityIntegrationTest {

    private List<SomeSubEntity> getTestSubList() {
        List<SomeSubEntity> subEntities = new ArrayList<>();
        SomeSubEntity subEntity = new SomeSubEntity();
        subEntity.setName("Sub one");
        subEntity.setContent("111111111");
        subEntities.add(subEntity);
        subEntity = new SomeSubEntity();
        subEntity.setName("Sub two");
        subEntity.setContent("22222222");
        subEntities.add(subEntity);
        subEntity = new SomeSubEntity();
        subEntity.setName("Sub three");
        subEntity.setContent("3333333");
        subEntities.add(subEntity);
        subEntity = new SomeSubEntity();
        subEntity.setName("Sub four");
        subEntity.setContent("44444444");
        subEntities.add(subEntity);
        return subEntities;
    }

    @Ignore
    @Test
    @Transactional
    @Rollback
    public void createSomeEntity() {
        SomeEntity someEntity = new SomeEntity();
        someEntity.setName("One");
        someEntity.setSubEntityList(getTestSubList());
        int sizeList = someEntity.getSubEntityList().size();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<SomeEntity> httpEntity = new HttpEntity<>(someEntity, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        SomeEntity resultEntity = restTemplate.exchange(
                "http://localhost:8080/some/add",
                HttpMethod.POST,
                httpEntity,
                SomeEntity.class
        ).getBody();

        Assert.assertNotNull(resultEntity);
        Assert.assertNotEquals(0L, resultEntity.getId());

        httpEntity = new HttpEntity<>(resultEntity, httpHeaders);
        resultEntity = restTemplate.exchange(
                String.format("http://localhost:8080/some/%d", resultEntity.getId()),
                HttpMethod.GET,
                httpEntity,
                SomeEntity.class
        ).getBody();

        Assert.assertNotNull(resultEntity);
        Assert.assertNotEquals(0, resultEntity.getSubEntityList().size());
        Assert.assertEquals(sizeList, resultEntity.getSubEntityList().size());

        httpEntity = new HttpEntity<>(resultEntity, httpHeaders);
        resultEntity = restTemplate.exchange(
                String.format("http://localhost:8080/some/%d", resultEntity.getId()),
                HttpMethod.DELETE,
                httpEntity,
                SomeEntity.class
        ).getBody();

        Assert.assertNotNull(resultEntity);

        httpEntity = new HttpEntity<>(resultEntity, httpHeaders);
        resultEntity = restTemplate.exchange(
                String.format("http://localhost:8080/some/%d", resultEntity.getId()),
                HttpMethod.GET,
                httpEntity,
                SomeEntity.class
        ).getBody();

        Assert.assertNull(resultEntity);
    }

}
