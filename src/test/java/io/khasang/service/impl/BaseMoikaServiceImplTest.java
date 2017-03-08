package io.khasang.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BaseMoikaService;
import io.khasang.moika.entity.ServiceType;
import io.khasang.moika.service.BaseMoikaServiceDataAccessService;
import io.khasang.moika.service.MoikaServiceTypesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class BaseMoikaServiceImplTest {

    @Autowired
    BaseMoikaServiceDataAccessService moikaService;


    @Test
    @Transactional
    public void testBaseMoikaServiceList(){
        List<BaseMoikaService> serviceList = null;
        try {
            serviceList = moikaService.getAllServices();
        } catch (MoikaDaoException e) {
            Assert.fail( e.getMessage());
        }
        Assert.assertNotNull("Service  list is null",serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isCode = false;
        for (BaseMoikaService item : serviceList) {
            if (item.getServiceName().equalsIgnoreCase("Ручная мойка машины")) {
                isCode = true;
            }
        }
        Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"",isCode);
    }
}
