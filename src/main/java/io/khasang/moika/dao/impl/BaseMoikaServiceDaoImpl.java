package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.BaseMoikaServiceDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BaseMoikaService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("baseMoikaServiceDao")
public class BaseMoikaServiceDaoImpl extends AllServiceDaoImpl<BaseMoikaService>  {

    public BaseMoikaServiceDaoImpl() {
    }


}
