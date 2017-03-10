package io.khasang.moika.service.impl;

import io.khasang.moika.dao.AllServiceDao;
import io.khasang.moika.dao.BaseMoikaServiceDao;
import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.AllService;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.AllServiceDataAccessService;
import io.khasang.moika.service.WashServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "allServiceDataAccessService")
@Transactional
public class AllServiceDataAccessServiceImpl extends AMoikaServiceDataAccessServiceImpl<AllService> implements AllServiceDataAccessService {
    @Autowired
    AllServiceDao serviceDao;

    public AllServiceDataAccessServiceImpl() {
    }

    @Autowired
    public AllServiceDataAccessServiceImpl(@Qualifier("allServiceDao") BaseMoikaServiceDao<AllService> baseMoikaServiceDao) {
        super(baseMoikaServiceDao);
        this.serviceDao = (AllServiceDao) baseMoikaServiceDao;
    }

}
