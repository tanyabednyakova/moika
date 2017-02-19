package io.khasang.moika.model;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

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
    public List<Pair<String,Integer>> withCaseQuery();
    public List<Pair<String, Integer>> withCaseQuery(Map<String, Integer> params);
    public List<Car> selectQuery(String type);
    public void updateQuery(Car car);
    public void insertQuery(Car car);
    public void insertQuery(List<Car> cars);
    public List<Pair<Car,Client>> joinQuery();
    public List<Car> withinSelectQuery();
    public void deleteQuery(Long id);
    public void deleteQuery(String carnumber);
    public void truncateQuery();
    public void doBackup(String path);
}
