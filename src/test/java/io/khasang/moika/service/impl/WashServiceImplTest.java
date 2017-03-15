package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.ServiceTypeDao;
import io.khasang.moika.entity.IBaseMoikaServiceAddInfo;
import io.khasang.moika.entity.MoikaService;
import io.khasang.moika.entity.ServiceType;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.MoikaServiceDataAccessService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class WashServiceImplTest {


    @Autowired
    MoikaServiceDataAccessService moikaService;
    @Autowired
    ServiceTypeDao serviceTypeDao;




    @Test
    @Transactional
    public void testWashServiceList() {
        List<MoikaService> serviceList = null;
        try {
            serviceList = moikaService.getServicesByType(1);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Service  list is null", serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isWashCode = false;
        BigDecimal cost = null;
        int dur = 0;
        for (MoikaService item : serviceList) {
            if (item.getId() == 11) {
                isWashCode = true;
                List<IBaseMoikaServiceAddInfo> addInfo = item.getServiceAddInfo();
                for (IBaseMoikaServiceAddInfo serviceInfo : addInfo) {
                    if (((WashService) serviceInfo).getCarTypeEntity().getTypeCode().equals("CAR")) {
                        cost = serviceInfo.getServiceCost();
                        dur = serviceInfo.getServiceDuration();
                        break;
                    }
                }
            }
        }
        Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"", isWashCode);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not cost", new BigDecimal("420.00").setScale(2), cost);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", 300, dur);
    }


    @Test
    @Transactional
    public void testAddWashService() {
        final String serviceName = "Мойка силой мысли";
        MoikaService service = new MoikaService(); // подготовили объект для тестирования

        service.setName(serviceName);
        service.setIdFacility(1);
        service.setDescription("к нам на полставки устроился Йода");
        service.setIdType(1);
        ServiceType stEntity = serviceTypeDao.get(service.getIdType());
        service.setServiceTypeEntity(stEntity);
        service.setIdStatus((short) 1);

        List<IBaseMoikaServiceAddInfo> serviceList = new ArrayList<>();
        WashService serviceAddInfo = new WashService();
        serviceAddInfo.setIdCarType(1);
        serviceAddInfo.setServiceCost(new BigDecimal("3500"));
        serviceAddInfo.setServiceDuration(5);
        serviceList.add(serviceAddInfo);

        serviceAddInfo = new WashService();
        serviceAddInfo.setIdCarType(2);
        serviceAddInfo.setServiceCost(new BigDecimal("5500"));
        serviceAddInfo.setServiceDuration(10);
        serviceList.add(serviceAddInfo);

        service.setServiceAddInfo(serviceList);

        MoikaService testService = new MoikaService(); // подготовили объект для возврата
        try {
            testService = moikaService.addService(service);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Wash servise is null", testService);
        boolean isWashCode = false;
        BigDecimal cost = null;
        int dur = 0;
        if (testService.getName().equalsIgnoreCase(serviceName)) {
            List<IBaseMoikaServiceAddInfo> addInfo = testService.getServiceAddInfo();
            Assert.assertEquals("Was service  list not contain ", 2, addInfo.size());
            isWashCode = true;
            for (IBaseMoikaServiceAddInfo serviceInfo : addInfo) {
                if (((WashService) serviceInfo).getCarTypeEntity().getTypeCode().equals("CAR")) {
                    cost = serviceInfo.getServiceCost();
                    dur = serviceInfo.getServiceDuration();
                    break;
                }
            }
        }
        Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"", isWashCode);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not cost", new BigDecimal("420.00").setScale(2), cost);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", 300, dur);
    }
}

