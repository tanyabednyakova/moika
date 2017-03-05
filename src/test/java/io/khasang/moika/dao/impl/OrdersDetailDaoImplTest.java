package io.khasang.moika.dao.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.OrdersDetailDao;
import io.khasang.moika.dao.WorkDao;
import io.khasang.moika.entity.OrdersDetail;
import io.khasang.moika.entity.Work;
import io.khasang.moika.service.WorkAccessService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class OrdersDetailDaoImplTest {
    @Autowired
    WorkDao workDao;
    @Autowired
    OrdersDetailDao ordersDetailDao;
    @Autowired
    WorkAccessService workAccessService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void commonOrdersDetail() throws Exception {
        Work work = new Work("Мытье кузова", new BigDecimal("333.333"), 30);
        workAccessService.addWork(work);
        work=new Work("Чистка салона",new BigDecimal("222.225"),0);
        workAccessService.addWork(work);

        OrdersDetail ordersDetail = new OrdersDetail();
//        ordersDetailDao.fillOrdersDetail(ordersDetail, work, new BigDecimal("3") );
//        workDao.updateWork(work);
//        ordersDetailDao.addOrdersDetail(ordersDetail);

//        ordersDetail = new OrdersDetail();
        ordersDetailDao.fillOrdersDetail(ordersDetail, work, new BigDecimal("5") );
        workDao.updateWork(work);
        ordersDetailDao.addOrdersDetail(ordersDetail);

        work = workDao.getWork("Мытье кузова");
        ordersDetail = new OrdersDetail();
        ordersDetailDao.fillOrdersDetail(ordersDetail, work, new BigDecimal("7") );
        workDao.updateWork(work);
        ordersDetailDao.addOrdersDetail(ordersDetail);
    }

}