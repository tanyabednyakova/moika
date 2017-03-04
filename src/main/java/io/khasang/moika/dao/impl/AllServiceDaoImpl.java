package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.AllServicesDao;
import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.MoikaAllService;
import io.khasang.moika.entity.WashService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("allServiceDao")
public class AllServiceDaoImpl extends AMoikaServiceDaoImpl<MoikaAllService> implements AllServicesDao{
    public AllServiceDaoImpl() {
    }

    public AllServiceDaoImpl(Class type) {
        super(type);
    }
}
