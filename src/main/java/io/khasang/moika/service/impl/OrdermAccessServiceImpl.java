package io.khasang.moika.service.impl;

import io.khasang.moika.dao.OrdermDao;
import io.khasang.moika.entity.Orderm;
import io.khasang.moika.service.OrdermAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("OrdermAccessServiceImpl")
@Transactional
public class OrdermAccessServiceImpl implements OrdermAccessService {
    @Autowired
    OrdermDao ordermDao;
    @Override
    public Orderm addOrderm(Orderm orderm) {
        return ordermDao.addOrderm(orderm);
    }

    @Override
    public Orderm updateOrderm(Orderm orderm) {
        return ordermDao.updateOrderm(orderm);
    }

    @Override
    public void deleteOrderm(Orderm orderm) {
        ordermDao.deleteOrderm(orderm);
    }

    @Override
    public Orderm getOrderm(long id) {
        return ordermDao.getOrderm(id);
    }

    @Override
    public List<Orderm> getAllOrderm() {
        return ordermDao.getAllOrderm();
    }
}
