package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.SomeEntityDao;
import io.khasang.moika.entity.SomeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SoneEntityDaoImpl extends MoikaDaoCrudImpl<SomeEntity> implements SomeEntityDao {
}
