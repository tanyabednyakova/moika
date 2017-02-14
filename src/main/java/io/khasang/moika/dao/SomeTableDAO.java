package io.khasang.moika.dao;

import io.khasang.moika.entity.SomeTable;
import io.khasang.moika.sometest.SomeTest;

import java.util.List;

/**
 * Created by blajimir on 14.02.2017.
 */
public interface SomeTableDAO {
    public SomeTable get(long id);
    public void save(SomeTest someTable);
    public void update(SomeTest someTable);
    public List<SomeTable> getAllSomeTable();
    public void delete(SomeTest someTable);
}
