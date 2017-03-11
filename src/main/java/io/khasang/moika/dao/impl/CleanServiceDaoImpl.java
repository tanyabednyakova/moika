package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CleanServiceDao;
import io.khasang.moika.entity.CleanService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("cleanServiceDao")
public class CleanServiceDaoImpl extends AllServiceFieldsDaoImpl<CleanService> implements CleanServiceDao {

    public CleanServiceDaoImpl() {
    }


}
