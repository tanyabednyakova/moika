package io.khasang.moika.dao.impl;

import io.khasang.moika.entity.BaseMoikaService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("baseMoikaServiceDao")
public class BaseMoikaServiceDaoImpl extends AllServiceFieldsDaoImpl<BaseMoikaService>  {

    public BaseMoikaServiceDaoImpl() {
    }


}
