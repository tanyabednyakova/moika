package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BaseMoikaServiceDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.impl.AllServiceDaoImpl;
import io.khasang.moika.entity.BaseMoikaService;
import io.khasang.moika.service.BaseMoikaServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "baseMoikaServiceDataAccessService")
@Transactional
public class BaseMoikaServiceDataAccessServiceImpl extends AMoikaServiceDataAccessServiceImpl implements BaseMoikaServiceDataAccessService {
    @Autowired
    private BaseMoikaServiceDao baseMoikaServiceDao;

    public BaseMoikaServiceDataAccessServiceImpl() {
    }
    @Autowired
    public BaseMoikaServiceDataAccessServiceImpl(
            @Qualifier("baseMoikaServiceDao") BaseMoikaServiceDao<BaseMoikaService> baseMoikaServiceDao) {
        super(baseMoikaServiceDao);
        this.baseMoikaServiceDao =  baseMoikaServiceDao;
    }
}
