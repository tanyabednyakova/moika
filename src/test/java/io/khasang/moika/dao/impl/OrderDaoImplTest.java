package io.khasang.moika.dao.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.OrderDao;
import io.khasang.moika.dao.OrdersDetailDao;
import io.khasang.moika.entity.Order;
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
public class OrderDaoImplTest {
    @Autowired
    OrderDao orderDao;
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
        Order order =new Order("1");
//        orderDao.addOrder(order);

    }

}