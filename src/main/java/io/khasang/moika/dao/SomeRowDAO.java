package io.khasang.moika.dao;

import io.khasang.moika.entity.SomeRow;

import java.util.List;

/**
 * Created by blajimir on 14.02.2017.
 */
public interface SomeRowDAO {
    public SomeRow get(long id);
    public SomeRow get(String name);
    public void save(SomeRow someRow);
    public void update(SomeRow someRow);
    public List<SomeRow> getAllSomeRow();
    public void delete(SomeRow someRow);
    public long countRow();
}
