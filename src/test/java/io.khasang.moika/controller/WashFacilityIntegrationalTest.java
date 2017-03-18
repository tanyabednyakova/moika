package io.khasang.moika.controller;

import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashFacility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class WashFacilityIntegrationalTest {

    @Ignore
    @Before
    public void initTests() {
        System.out.println("Tests are beginning...");
    }

    final String fcltName = "Мока на Фонтанке";
    final int id = 1;

    @Ignore
    @Test
    @Transactional
    @Rollback
    public void createTestFacility() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        WashFacility fclt = new WashFacility(); // подготовили класс для тестирования

        fclt.setName(fcltName);
        fclt.setIdNet(1);
        fclt.setDescription("не фортан");
        fclt.setIdAddr(3);

        List<WashBox> boxList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            WashBox box = new WashBox();
            box.setBoxName("№ " + i);
            box.setBoxStatusEntity(new BoxStatus("WORKING"));
            box.setDescription(box.getBoxName() + " " + fclt.getName());
            box.setBoxTypeEntity(new BoxType("CAR"));
            boxList.add(box);
        }

        fclt.setWashBoxes(boxList);

        HttpEntity httpEntity = new HttpEntity<>(fclt, headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();
        WashFacility resultFclt = restTemplate.exchange(    //отправли запрос через веб (т.е. снаружи приложения)
                "http://localhost:8080/washFacility/add",
                HttpMethod.POST,
                httpEntity,
                WashFacility.class).getBody();

        Assert.assertNotNull(resultFclt);
        Assert.assertEquals(fcltName, resultFclt.getName());
}

    @Test
    public void getFcltById() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<List<WashFacility>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<WashFacility>> resultAll = restTemplate.exchange(
                "http://localhost:8080/washFacility/{id}/",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<WashFacility>>() {
                }, id);
        Assert.assertNotNull(resultAll);
        List<WashFacility> fcltList = resultAll.getBody();
        Assert.assertNotNull(fcltList);
        Assert.assertEquals(fcltName,  fcltList.get(0).getName());
    }
}
