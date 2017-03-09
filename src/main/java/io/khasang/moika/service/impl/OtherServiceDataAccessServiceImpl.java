package io.khasang.moika.service.impl;


import io.khasang.moika.dao.BaseMoikaServiceDao;
import io.khasang.moika.dao.OtherServiceDao;
import io.khasang.moika.entity.OtherService;
import io.khasang.moika.service.OtherServiceDataAccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "otherServiceDataAccessService")
@Transactional
public class OtherServiceDataAccessServiceImpl extends AMoikaServiceDataAccessServiceImpl<OtherService> implements OtherServiceDataAccessService {
    @Autowired
    OtherServiceDao serviceDao;

    public OtherServiceDataAccessServiceImpl() {
    }

    @Autowired
    public OtherServiceDataAccessServiceImpl(@Qualifier("otherServiceDao") BaseMoikaServiceDao<OtherService> baseMoikaServiceDao) {
        super(baseMoikaServiceDao);
        this.serviceDao = (OtherServiceDao) baseMoikaServiceDao;
    }

}
