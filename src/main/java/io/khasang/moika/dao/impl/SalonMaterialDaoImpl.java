package io.khasang.moika.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository("serviceMaterialDaoImpl")
public class SalonMaterialDaoImpl<ServiceType> extends AMoikaTypeReferenceDaoImpl {

    public SalonMaterialDaoImpl() {
        super();
    }
}