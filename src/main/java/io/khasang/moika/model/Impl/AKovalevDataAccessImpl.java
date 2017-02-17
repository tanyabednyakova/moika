package io.khasang.moika.model.Impl;

import io.khasang.moika.entity.Car;
import io.khasang.moika.model.AKovalevDataAccess;

import java.util.List;

/**
 * Created by Благомир on 18.02.2017.
 */
public class AKovalevDataAccessImpl implements AKovalevDataAccess {
    @Override
    public String[] withCaseQuery() {
        return new String[0];
    }

    @Override
    public List<Car> selectQuery(String type) {
        return null;
    }

    @Override
    public void updateQuery(Long fromId, String descr) {

    }

    @Override
    public void insertQuery(Car car) {

    }

    @Override
    public List<Car> innerSelectQuery() {
        return null;
    }

    @Override
    public void deleteQuery(Long id) {

    }

    @Override
    public void deleteQuery(String number) {

    }

    @Override
    public void truncateQuery() {

    }

    @Override
    public void doBackup() {

    }
}
