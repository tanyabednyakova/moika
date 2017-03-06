package io.khasang.service;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.ServiceType;
import io.khasang.moika.entity.WashService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class WashServiceImplTest {
    @Autowired
    @Qualifier("washServiceDao")
    WashServiceDao washServiceDao;

    @Ignore
    @Test
    @Transactional
    public void testGetAllWashServices() {
        List<WashService> allServicesList = new ArrayList<>();
        try {
            allServicesList = washServiceDao.getAllEntities();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        assertNotNull("List is Null", allServicesList);
        assertTrue("List is empty", allServicesList.isEmpty());

    }

    @Ignore
    @Test
    @Transactional
    public void testGetAllServicesTypes() {
        List<WashService> allServicesList = new ArrayList<>();
        try {
            allServicesList = washServiceDao.getAllEntities();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        assertNotNull("List is Null", allServicesList);
        assertTrue("List is empty", allServicesList.isEmpty());

    }
}