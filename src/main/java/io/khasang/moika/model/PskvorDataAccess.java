package io.khasang.moika.model;

import java.util.List;

/**
 * Created by Pauls on 17.02.2017.
 */
public interface PskvorDataAccess {
    List<String> readData(String tableName);
    List<String> readData(String tableName, String cond, Object[] args);
    String addData(String tableName, Object[] args );
    String addDataReturnId(String tableName, Object[] args );
    String changeData(String tableName, String[] fileds, String cond, Object[] args);
    String createData(String tableName, String[] fields, String[] fieldsTypes, String constraint);
    String deleteData(String tableName, String cond, Object[] args);
    List<String> joinData(String tableName1, String tableName2, String joinfield, String condition, Object[] args);
    List<String> innerSelectData(String tableName1, String tableName2, String inField, String wherefield, String condition, Object[] args);
    List<String> caseSelectData(String tableName, String field, String caseWhen, String caseThen, String caseElse, String condition, Object[] args);
    String backupData(String fileName);
}
