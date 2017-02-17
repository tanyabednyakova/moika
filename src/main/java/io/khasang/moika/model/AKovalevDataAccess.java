package io.khasang.moika.model;

import io.khasang.moika.entity.Car;

import java.util.List;

/**
 * Created by Благомир on 18.02.2017.
 * Дмитрий Орлов - Select
 * Скворцов Павел - Update
 * Александр Иванов - Insert
 * Ростислав Дублин - JOIN
 * Беднякова Татьяна - Вложенный select
 * Ковалев Александр (Благомир Кузнец) - запрос с условием (CASE)
 * Ильичёв Николай - Delete
 * Мадорин Владимир - Truncate
 * Александр Любарев - backup, через pgdump *
 */
public interface AKovalevDataAccess {
    public String[] withCaseQuery();
    public List<Car> selectQuery(String type);
    public void updateQuery(Long fromId, String descr);
    public void insertQuery(Car car);
    //public ref joinQuery();
    public List<Car> innerSelectQuery();
    public void deleteQuery(Long id);
    public void deleteQuery(String number);
    public void truncateQuery();
    public void doBackup();
}
