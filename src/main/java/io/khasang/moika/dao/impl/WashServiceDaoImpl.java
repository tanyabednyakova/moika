package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.BaseMoikaServiceDao;
import io.khasang.moika.dao.WashFacilityDao;
import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.BaseMoikaService;
import io.khasang.moika.entity.WashService;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("washServiceDao")
public class WashServiceDaoImpl extends AllServiceDaoImpl<WashService> implements WashServiceDao {

    public WashServiceDaoImpl() {
    }


}
