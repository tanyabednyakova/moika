package io.khasang.moika.dao.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.OrdermDao;
import io.khasang.moika.dao.OrdersDetailDao;
import io.khasang.moika.entity.Orderm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class OrdermDaoImplTest {
    @Autowired
    OrdermDao ordermDao;
    @Autowired
    OrdersDetailDao ordersDetailDao;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void commonOrder() throws Exception {
        Orderm orderm =new Orderm("1");
        ordermDao.addOrderm(orderm);
        orderm =new Orderm("2");
        ordermDao.addOrderm(orderm);
        orderm = ordermDao.getOrderm(1l);
        orderm.setNumber("11");
        ordermDao.updateOrderm(orderm);
        orderm =new Orderm("33");
        orderm =ordermDao.addOrderm(orderm);
        ordermDao.deleteOrderm(orderm);


    }

}