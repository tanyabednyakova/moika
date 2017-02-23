package io.khasang.moika.model.impl;

import io.khasang.moika.model.RostislavDataAccess;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RostislavDataAccessImpl implements RostislavDataAccess {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public RostislavDataAccessImpl() {
    }

    @Override

    public List<Map<String, Object>> getSelectedDataFromDbEntity(
            String tableName,
            Map<String, Pair<String, String>> filters
    ) {

        final StringBuilder sqlQueryBuilder = new StringBuilder("select * from ").append(tableName);
        final ArrayList<String> filterValues = new ArrayList<>();

        if (filters != null && !filters.isEmpty()) {
            sqlQueryBuilder.append(" where ");

            String where = filters.entrySet().stream()
                    .map(condition -> {
                        filterValues.add(condition.getValue().getValue());
                        return condition.getKey() + " " + condition.getValue().getKey() + " ?";
                    })
                    .collect(Collectors.joining(" and "));

            sqlQueryBuilder.append(where);

        }

        if(!filterValues.isEmpty()) {
            return jdbcTemplate.queryForList(sqlQueryBuilder.toString(), filterValues.toArray());
        } else{
            return jdbcTemplate.queryForList(sqlQueryBuilder.toString());
        }

    }

    public void getSelectedDataFromJoinedDbEntities(String leftTableName, String rightTableName, Map<String,
            Pair<String, String>> joinType, Map<String, Pair<String, String>> filters) {
        //TODO: реализовать метод
   /*     throw new RuntimeException("Mетод ещё не реализован!");
            prepareFilter(filters, sqlQueryBuilder, filterValues);
        }

        if (!filterValues.isEmpty()) {
            return jdbcTemplate.queryForList(sqlQueryBuilder.toString(), filterValues.toArray());
        } else {
            return jdbcTemplate.queryForList(sqlQueryBuilder.toString());
        }*/

    }

    @Override
    public List<Map<String, Object>> getSelectedDataFromJoinedDbEntities(
            String leftTableName,
            String rightTableName,
            String joinType,
            Map<String, Pair<String, String>> joiners,
            Map<String, Pair<String, String>> filters
    ) {

        final StringBuilder sqlQueryBuilder = new StringBuilder("select * from ")
                .append(leftTableName).append(" ").append(joinType).append(" join ").append(rightTableName);

        final ArrayList<String> filterValues = new ArrayList<>();

        if (joiners != null && !joiners.isEmpty()) {
            sqlQueryBuilder.append(" on ");
            prepareFilter(joiners, sqlQueryBuilder, filterValues);
        }

        if (filters != null && !filters.isEmpty()) {
            sqlQueryBuilder.append(" where ");
            prepareFilter(filters, sqlQueryBuilder, filterValues);
        }


        if (!filterValues.isEmpty()) {
            return jdbcTemplate.queryForList(sqlQueryBuilder.toString(), filterValues.toArray());
        } else {
            return jdbcTemplate.queryForList(sqlQueryBuilder.toString());
        }
    }

    /**
     * Служебный метод для построения секции WHERE
     *
     * @param filters         опциональный набор элементов фильтрации в виде карты "имя поля -> пара: оператор, значение". Если задана непустая карта, то фильтры при меняются по принципу "И" и по условию "значение поля равно переданному заначению фильтра"
     * @param sqlQueryBuilder построитель строки которому надо добавить формулировку условий фильтрации
     * @param filterValues    коллекция значений элементов фильтрации, которые нужно наполнить из набора элементов @filters
     */
    private void prepareFilter(
            Map<String, Pair<String, String>> filters,
            StringBuilder sqlQueryBuilder,
            ArrayList<String> filterValues
    ) {

        String where = filters.entrySet().stream()
                .map(condition -> {
                    filterValues.add(condition.getValue().getValue());
                    return condition.getKey() + " " + condition.getValue().getKey() + " ?";
                })
                .collect(Collectors.joining(" and "));

        sqlQueryBuilder.append(where);


    }
}
