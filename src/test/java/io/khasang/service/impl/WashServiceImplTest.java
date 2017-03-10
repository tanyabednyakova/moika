package io.khasang.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BaseMoikaService;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.BaseMoikaServiceDataAccessService;
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
public class WashServiceImplTest {

    @Autowired
    WashServiceDataAccessService moikaService;


    @Test
    @Transactional
    public void testWashServiceList(){
        List<WashService> serviceList = null;
        try {
            serviceList = moikaService.getAllServices();
        } catch (MoikaDaoException e) {
            Assert.fail( e.getMessage());
        }
        Assert.assertNotNull("Service  list is null",serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isCode = false;
        BigDecimal washCost = null;
        for (WashService item : serviceList) {
            if (item.getServiceName().equalsIgnoreCase("Ручная мойка машины")) {
                isCode = true;
                washCost = item.getServiceCost();
                System.out.println(item.toString());
            }
        }
        Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"",isCode);
        Assert.assertNotEquals("Service types list  name \"Ручная мойка машины\" not cost",BigDecimal.valueOf(450).setScale(0),washCost);
    }
}
