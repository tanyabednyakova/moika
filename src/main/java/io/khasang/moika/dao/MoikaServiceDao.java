package io.khasang.moika.dao;

import io.khasang.moika.entity.BaseMoikaService;
import io.khasang.moika.entity.MoikaAllService;
import io.khasang.moika.entity.MoikaService;

import java.util.List;

public interface MoikaServiceDao extends IMoikaDaoCrud<MoikaAllService>{
    List<MoikaService> getActualMoikaServices() throws MoikaDaoException;
}
