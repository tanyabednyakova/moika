package io.khasang.moika.service.impl;

import io.khasang.moika.model.MadvDataAcces;
import io.khasang.moika.service.MadvDataAccesService;

/**
 * Created by madv on 20.02.2017.
 */
public class MadvDataAccesServiceImpl implements MadvDataAccesService {
    private MadvDataAcces madvDataAcces;

    public MadvDataAccesServiceImpl() {
    }

    public MadvDataAccesServiceImpl(MadvDataAcces madvDataAcces) {
        this.madvDataAcces = madvDataAcces;
    }

    public MadvDataAcces getMadvDataAcces() {
        return madvDataAcces;
    }

    public void setMadvDataAcces(MadvDataAcces madvDataAcces) {
        this.madvDataAcces = madvDataAcces;
    }

    @Override
    public String test() {
        return "Полная жопа. " + madvDataAcces.truncate("cars");
    }
}
