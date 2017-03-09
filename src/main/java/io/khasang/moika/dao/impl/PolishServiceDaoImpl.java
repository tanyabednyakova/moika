package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.PolishServiceDao;
import io.khasang.moika.entity.PolishService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("polishServiceDao")
public class PolishServiceDaoImpl extends AllServiceFieldsDaoImpl<PolishService> implements PolishServiceDao {


    public PolishServiceDaoImpl() {
    }


}
