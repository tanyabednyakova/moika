package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.BaseMoikaServiceDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.ServiceTypeDao;
import io.khasang.moika.entity.BaseMoikaService;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.ServiceType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("ServiveTypeDao")
public class ServiceTypeDaoImpl extends AllTypeDaoImpl<ServiceType> implements ServiceTypeDao {

    public ServiceTypeDaoImpl() {
    }



}
