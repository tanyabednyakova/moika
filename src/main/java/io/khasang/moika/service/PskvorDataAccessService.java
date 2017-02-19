package io.khasang.moika.service;

import io.khasang.moika.model.PskvorDataAccess;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Pauls on 17.02.2017.
 */
@Service
public class PskvorDataAccessService {
    PskvorDataAccess pskvorDataAccess;

    public PskvorDataAccessService() {
    }

    public PskvorDataAccessService(PskvorDataAccess pskvorDataAcces) {
        this.pskvorDataAccess = pskvorDataAcces;
    }

    public String getRowsAsString(List<String> strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
            sb.append("<br>");
        }
        sb.append(strings.size() - 1 + " rows selected <br>");
        return sb.toString();
    }

    public PskvorDataAccess getPskvorDataAccess() {
        return pskvorDataAccess;
    }

    public void setPskvorDataAccess(PskvorDataAccess pdasi) {
        this.pskvorDataAccess = pdasi;
    }

    public String select(String tableName) {
        List<String> strings = pskvorDataAccess.readData(tableName);
        return getRowsAsString(strings);
    }

    public String select(String tableName, String cond, Object[] args) {
        List<String> strings = pskvorDataAccess.readData(tableName, cond, args);
        return getRowsAsString(strings);
    }

    public String createData(String tableName, String[] fields, String[] fieldsTypes, String constraint) {
        String res = pskvorDataAccess.createData(tableName, fields, fieldsTypes, constraint);
        return res;
    }

    public String changeData(String tableName, String[] fields, String cond, Object[] args) {
        String res = pskvorDataAccess.changeData(tableName, fields, cond, args);
        return res;
    }

    public String addData(String tableName, Object[] args){
        String res = pskvorDataAccess.addData(tableName, args);
        return res;
    }

    public String joinSelect(String tableName1, String tableName2, String joinfield, String condition, Object[] args){
        List<String> strings = pskvorDataAccess.joinData(tableName1,tableName2, joinfield, condition, args);
        return getRowsAsString(strings);
    }

    public String deleteData(String tableName, String cond, Object[] args) {
        String res = pskvorDataAccess.deleteData(tableName, cond, args);
        return res;
    }

    public String backupData(String fileName) {
        String res = pskvorDataAccess.backupData(fileName);
        return res;
    }
}
