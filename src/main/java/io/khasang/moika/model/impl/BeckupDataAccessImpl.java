package io.khasang.moika.model.impl;

import io.khasang.moika.model.BackupDataAccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alyubarev on 19.02.2017.
 */

public class BeckupDataAccessImpl implements BackupDataAccess {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BeckupDataAccessImpl(JdbcTemplate jdbcTemplate) {


        this.jdbcTemplate = jdbcTemplate;
    }

    public BeckupDataAccessImpl() {
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String backupData() {
        try {
            String BaseName = jdbcTemplate.getDataSource().getConnection().getCatalog();
            List<String> cmdCommands = new ArrayList<String>();
            //cmdCommands.add("C:\\Program Files\\PostgreSQL\\9.5\\bin\\pg_dump.exe");
            cmdCommands.add("pg_dump.exe");
            cmdCommands.add("-i");
            cmdCommands.add("-h");
            cmdCommands.add("localhost");
            cmdCommands.add("-p");
            cmdCommands.add("5432");
            cmdCommands.add("-U");
            cmdCommands.add(BaseName);
            cmdCommands.add("-F");
            cmdCommands.add("c");
            cmdCommands.add("-b");
            cmdCommands.add("-v");
            cmdCommands.add("-f");
            cmdCommands.add("\\");
            cmdCommands.add(" C:\\Proj\\Java");
            //cmdCommands.add("lmd");
            ProcessBuilder process = new ProcessBuilder();
            process.command(cmdCommands).start();
            //pg_dump -i -h localhost -p 5432 -U postgres -F c -b -v -f
            //pg_dump.exe -i -h localhost -p 5432 -U postgres -F c -v -f "C:\Proj\Java\base.backup"

  /*
-p, –port=PORT database server port number
-i, –ignore-version proceed even when server version mismatches
-h, –host=HOSTNAME database server host or socket directory
-U, –username=NAME connect as specified database user
-W, –password force password prompt (should happen automatically)
-d, –dbname=NAME connect to database name
-v, –verbose verbose mode
-F, –format=c|t|p output file format (custom, tar, plain text)
-c, –clean clean (drop) schema prior to create
-b, –blobs include large objects in dump
-v, –verbose verbose mode
-f, –file=FILENAME output file name */

            return "Backup is created";
        } catch (Exception e) {
            return "Backup creation failed: " + e;
        }
    }

}
