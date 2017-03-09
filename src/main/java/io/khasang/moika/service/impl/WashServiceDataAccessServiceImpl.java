package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BaseMoikaServiceDao;
import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.impl.AllServiceDaoImpl;
import io.khasang.moika.dao.impl.WashServiceDaoImpl;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.WashServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "washServiceDataAccessService")
@Transactional
public class WashServiceDataAccessServiceImpl extends AMoikaServiceDataAccessServiceImpl<WashService> implements WashServiceDataAccessService {
    @Autowired
    WashServiceDao washServiceDao;

    public WashServiceDataAccessServiceImpl() {
    }

    @Autowired
    public WashServiceDataAccessServiceImpl( @Qualifier("washServiceDao") BaseMoikaServiceDao<WashService> baseMoikaServiceDao) {
        super(baseMoikaServiceDao);
        this.washServiceDao = (WashServiceDao) baseMoikaServiceDao;
    }

}
