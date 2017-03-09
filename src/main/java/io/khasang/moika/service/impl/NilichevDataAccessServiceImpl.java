package io.khasang.moika.service.impl;

import io.khasang.moika.model.NilichevDataAccess;
import io.khasang.moika.service.NilichevDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CarServiceImpl")
@Transactional
public class NilichevDataAccessServiceImpl implements NilichevDataAccessService{
    @Autowired
    NilichevDataAccess nilichevDataAccess;

    @Override
    public void deleteCar(long id) {
        nilichevDataAccess.deleteCar(id);
    }
}
