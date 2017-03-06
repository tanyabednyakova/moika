package io.khasang.moika.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository("dirtTypeDaoImpl")
public class DirtTypeDaoImpl<DirtType> extends AMoikaTypeReferenceDaoImpl {

    public DirtTypeDaoImpl() {
        super();
    }
}