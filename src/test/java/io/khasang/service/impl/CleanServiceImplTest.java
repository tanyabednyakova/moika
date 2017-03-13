package io.khasang.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.CleanService;
import io.khasang.moika.service.CleanServiceDataAccessService;
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
public class CleanServiceImplTest {

    @Autowired
    CleanServiceDataAccessService moikaService;


    @Test
    @Transactional
    public void testCleanServiceList(){
        List<CleanService> serviceList = null;
        try {
            serviceList = moikaService.getAllServices();
        } catch (MoikaDaoException e) {
            Assert.fail( e.getMessage());
        }
        Assert.assertNotNull("Service  list is null",serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isCode = false;
        BigDecimal cleanCost = null;
        for (CleanService item : serviceList) {
            if (item.getServiceName().equalsIgnoreCase("Чиска салона")) {
                isCode = true;
                cleanCost = item.getServiceCost();
            }
        }
        Assert.assertTrue("Service types list not contain name \"Чиска салона\"",isCode);
        Assert.assertNotEquals("Service types list not contain name \"Чиска салона\"",BigDecimal.valueOf(500).setScale(0),cleanCost);
    }
}
