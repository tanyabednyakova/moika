package io.khasang.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.Car;
import io.khasang.moika.service.CarService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class CarServiceImplTest {

    @Autowired
    CarService carService;


    @Test
    @Transactional
    public void testCleanServiceList(){
        List<Car> carsList = carService.getCarList();

        Assert.assertNotNull("Service  list is null",carsList);
        Assert.assertFalse("Service  list is empty", carsList.isEmpty());
        boolean isCode = false;
        BigDecimal cleanCost = null;
        for (Car car : carsList) {
            if (car.getCarNumber().equalsIgnoreCase("М335РО99RUS")) {
                isCode = true;
            }
        }
        Assert.assertTrue("Cars list not contain car N \"М335РО99RUS\"",isCode);
    }
}
