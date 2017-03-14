package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.SomeSubEntityDao;
import io.khasang.moika.entity.SomeSubEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SomeSubEntityDaoImpl extends MoikaDaoCrudImpl<SomeSubEntity> implements SomeSubEntityDao {
}
