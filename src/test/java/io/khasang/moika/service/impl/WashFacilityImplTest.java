package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
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


    @Test
    @Transactional
    public void testWashFacilityServiceList() {
        final String testString = "Мойка на Мойке";
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
            if (item.getName().equalsIgnoreCase("Мойка на Мойке")) {
                isWashFacility = true;
                List<WashBox> boxList = item.getWashBoxes();
                for (WashBox box : boxList) {
                    if (box.getBoxName().equalsIgnoreCase("Бокс 1")) {
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
        final String fcltName = "Мока на Фонтанке";
        WashFacility fclt = new WashFacility(); // подготовили класс для тестирования

        fclt.setName(fcltName);
        fclt.setIdNet(1);
        fclt.setDescription("не фонтан");
        fclt.setIdAddr(3);

        List<WashBox> boxList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            WashBox box = new WashBox();
            box.setBoxName("№ " + i);
            box.setIdStatus((short) 1);
            box.setIdtype(1);
            // box.setBoxStatusEntity(new BoxStatus("WORKING"));
            box.setDescription(box.getBoxName() + " " + fclt.getName());
            // box.setBoxTypeEntity(new BoxType("CAR"));
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
        if (resFclt.getName().equalsIgnoreCase(fcltName)) {
            Assert.assertEquals("Facility   not contain boxes", 4, resFclt.getWashBoxes().size());
            List<WashBox> resBoxList = resFclt.getWashBoxes();
            for (WashBox box : boxList) {
                if (box.getBoxName().equalsIgnoreCase("№ 1")) {
                    isBox = true;
                    break;
                }
            }
        }
        Assert.assertTrue("Facility  not contain " + fcltName, resFclt.getName().equalsIgnoreCase(fcltName));
        Assert.assertTrue("Facility  not contain box", isBox);
    }
}
