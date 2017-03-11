package io.khasang.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ChemCleanService;
import io.khasang.moika.service.ChemCleanServiceDataAccessService;
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
public class ChemCleanServiceImplTest {

    @Autowired
    ChemCleanServiceDataAccessService moikaService;


    @Test
    @Transactional
    public void testChemCleanServiceList(){
        List<ChemCleanService> serviceList = null;
        try {
            serviceList = moikaService.getAllServices();
        } catch (MoikaDaoException e) {
            Assert.fail( e.getMessage());
        }
        Assert.assertNotNull("Service  list is null",serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isCode = false;
        BigDecimal chemCleanCost = null;
        for (ChemCleanService item : serviceList) {
            if (item.getServiceName().equalsIgnoreCase("Химчиска салона")) {
                isCode = true;
                chemCleanCost = item.getServiceCost();
            }
        }
        Assert.assertTrue("Service types list not contain name \"Химчиска салона\"",isCode);
        Assert.assertNotEquals("Service types list not contain name \"Химчиска салона\"",BigDecimal.valueOf(500).setScale(0),chemCleanCost);
    }
}
