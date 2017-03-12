package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BaseMoikaServiceDao;
import io.khasang.moika.dao.PolishServiceDao;
import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.PolishService;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.PolishServiceDataAccessService;
import io.khasang.moika.service.WashServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "polishServiceDataAccessService")
@Transactional
public class PolishServiceDataAccessServiceImpl extends AMoikaServiceDataAccessServiceImpl<PolishService> implements PolishServiceDataAccessService {
    @Autowired
    PolishServiceDao polishServiceDao;

    public PolishServiceDataAccessServiceImpl() {
    }

    @Autowired
    public PolishServiceDataAccessServiceImpl(@Qualifier("polishServiceDao") BaseMoikaServiceDao<PolishService> baseMoikaServiceDao) {
        super(baseMoikaServiceDao);
        this.polishServiceDao = (PolishServiceDao) baseMoikaServiceDao;
    }

}
