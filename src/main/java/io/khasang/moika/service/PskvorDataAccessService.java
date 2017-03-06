package io.khasang.moika.service;

import io.khasang.moika.model.PskvorDataAccess;
import org.springframework.stereotype.Service;

import java.util.List;

public class PskvorDataAccessService {
    private PskvorDataAccess pskvorDataAccess;

    public PskvorDataAccessService() {
    }

    public PskvorDataAccessService(PskvorDataAccess pskvorDataAccess) {
        this.pskvorDataAccess = pskvorDataAccess;
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

    public void setPskvorDataAccess(PskvorDataAccess pskvorDataAccess) {
        this.pskvorDataAccess = pskvorDataAccess;
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

    public String joinSelect(List<String> tableNames, List<String[]> joinFields, String condition, Object[] args){
        List<String> strings = pskvorDataAccess.joinData(tableNames, joinFields, condition, args);
        return getRowsAsString(strings);
    }

    public String deleteData(String tableName, String cond, Object[] args) {
        String res = pskvorDataAccess.deleteData(tableName, cond, args);
        return res;
    }

    public String backupData(String pgDumpPath, String fileName, boolean isExecute) {
        String res = pskvorDataAccess.backupData(pgDumpPath, fileName, false);
        return res;
    }
}
