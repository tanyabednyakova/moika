package io.khasang.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.AllService;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.AllServiceDataAccessService;
import io.khasang.moika.service.WashServiceDataAccessService;
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
public class AllServiceImplTest {

    @Autowired
    AllServiceDataAccessService moikaService;


    @Test
    @Transactional
    public void testAllServiceList(){
        List<AllService> serviceList = null;
        try {
            serviceList = moikaService.getAllServices();
        } catch (MoikaDaoException e) {
            Assert.fail( e.getMessage());
        }
        Assert.assertNotNull("Service  list is null",serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isWashCode = false;
        BigDecimal washCost = null;
        boolean isCleanCode = false;
        BigDecimal cleanCost = null;
        for (AllService item : serviceList) {
            if (item.getServiceName().equalsIgnoreCase("Ручная мойка машины")) {
                isWashCode = true;
                washCost = item.getServiceCost();
            }
            if (item.getServiceName().equalsIgnoreCase("Чиска салона")) {
                isCleanCode = true;
                cleanCost = item.getServiceCost();
            }
        }
        Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"",isWashCode);
        Assert.assertNotEquals("Service types list  name \"Ручная мойка машины\" not cost",BigDecimal.valueOf(350).setScale(0),washCost);
        Assert.assertTrue("Service types list not contain name \"Чиска салона\"",isCleanCode);
        Assert.assertNotEquals("Service types list  name \"Чиска салона\" not cost",BigDecimal.valueOf(500).setScale(0),cleanCost);
    }
}
