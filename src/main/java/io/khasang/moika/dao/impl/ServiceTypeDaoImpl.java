package io.khasang.moika.dao.impl;



import io.khasang.moika.dao.ServiceTypeDao;
import io.khasang.moika.entity.ServiceType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository("serviceTypeDaoImpl")
public class ServiceTypeDaoImpl extends AMoikaTypeReferenceDaoImpl<ServiceType> implements ServiceTypeDao{

    public ServiceTypeDaoImpl() {
        super();
    }
}