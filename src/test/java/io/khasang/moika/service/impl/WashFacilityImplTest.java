package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.BoxStatusDao;
import io.khasang.moika.dao.BoxTypeDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashFacility;
import io.khasang.moika.service.PskvorWashFacilityDaoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class WashFacilityImplTest {


    @Autowired
    PskvorWashFacilityDaoService fcltService;

    @Autowired
    BoxStatusDao boxStatusDao;

    @Autowired
    BoxTypeDao boxTypeDao;


    @Test
    @Transactional
    public void testWashFacilityServiceList() {
        final String testString = "Мойка на Фонтанке";
        List<WashFacility> fcltList = null;
        try {
            fcltList = fcltService.getWashFacilitiesOnNet(1);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Facility  list is null", fcltList);
        Assert.assertFalse("Facility  list is empty", fcltList.isEmpty());
        boolean isWashFacility = false;
        boolean isBox = false;
        int dur = 0;
        for (WashFacility item : fcltList) {
            if (item.getName().equalsIgnoreCase(testString)) {
                isWashFacility = true;
                List<WashBox> boxList = item.getWashBoxes();
                for (WashBox box : boxList) {
                    if (box.getBoxName().equalsIgnoreCase("№ 1")) {
                        isBox = true;
                        break;
                    }
                }
            }
        }
        Assert.assertTrue("Facility  list not contain " + testString, isWashFacility);
        Assert.assertTrue("Facility  list not contain box", isBox);
    }

    @Test
    @Transactional
    public void testAddWashFacility() {
        final String fcltName = "Мойка test";
        final String stausCode = "WORKING";
        final String typeCode = "MEDIUM";
        WashFacility fclt = new WashFacility(); // подготовили класс для тестирования

        fclt.setName(fcltName);
        fclt.setIdNet(1);
        fclt.setDescription("моет тех, кто чешется");
        fclt.setIdAddr(3);

        BoxStatus boxStatus = boxStatusDao.getEntityByCode(stausCode);
        BoxType boxType = boxTypeDao.getEntityByCode(typeCode);
        List<WashBox> boxList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            WashBox box = new WashBox();
            box.setBoxName("Бокс № " + i);
      //      box.setIdStatus((short) 1);
      //      box.setIdtype(1);
            box.setDescription(box.getBoxName() + " " + fclt.getName());
            box.setBoxStatusEntity(boxStatus);
            box.setBoxTypeEntity(boxType);
            boxList.add(box);
        }

        fclt.setWashBoxes(boxList);

        WashFacility resFclt = new WashFacility();
        try {
            resFclt = fcltService.addWashFacility(fclt);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Facility is null", resFclt);
        boolean isBox = false;
        boolean isType;
        boolean isStatus;
        if (resFclt.getName().equalsIgnoreCase(fcltName)) {
            Assert.assertEquals("Facility   not contain boxes", 4, resFclt.getWashBoxes().size());
            List<WashBox> resBoxList = resFclt.getWashBoxes();
            for (WashBox box : boxList) {
                if (box.getBoxName().equalsIgnoreCase("Бокс № 1")) {
                    isBox = true;
                    Assert.assertTrue("Facility  box status not "+stausCode,box.getBoxStatusEntity().getStatusCode().equalsIgnoreCase(stausCode));
                    Assert.assertTrue("Facility  box type not "+typeCode, box.getBoxTypeEntity().getTypeCode().equalsIgnoreCase(typeCode));
                    break;
                }
            }
        }
        Assert.assertTrue("Facility  not contain " + fcltName, resFclt.getName().equalsIgnoreCase(fcltName));
        Assert.assertTrue("Facility  not contain box", isBox);
    }
}
