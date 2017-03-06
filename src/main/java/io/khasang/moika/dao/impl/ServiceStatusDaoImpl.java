package io.khasang.moika.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository("serviceStatusDaoImpl")
public class ServiceStatusDaoImpl<ServiceStatus> extends AMoikaStatusDaoImpl {

    public ServiceStatusDaoImpl() {
        super();
    }
}