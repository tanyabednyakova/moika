package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.AllServiceDao;
import io.khasang.moika.entity.AllService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("allServiceDao")
public class AllServiceDaoImpl extends AllServiceFieldsDaoImpl<AllService> implements AllServiceDao {

    public AllServiceDaoImpl() {
    }


}
