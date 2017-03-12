package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.OtherServiceDao;
import io.khasang.moika.entity.OtherService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("otherServiceDao")
public class OtherServiceDaoImpl extends AllServiceFieldsDaoImpl<OtherService> implements OtherServiceDao{

    public OtherServiceDaoImpl() {
        super();
    }


}
