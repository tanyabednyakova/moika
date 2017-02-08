package io.khasang.moika.model;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by blajimir on 09.02.2017.
 */
public class CreateTable {
    private JdbcTemplate jdbcTemplate;

    public CreateTable() {
    }

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String create() {
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS films");
            jdbcTemplate.execute("CREATE TABLE films (\n" +
                    "    code        char(5) CONSTRAINT firstkey PRIMARY KEY,\n" +
                    "    title       varchar(40) NOT NULL,\n" +
                    "    did         integer NOT NULL,\n" +
                    "    date_prod   date,\n" +
                    "    kind        varchar(10),\n" +
                    "    len         interval hour to minute\n" +
                    ");");
            return "Table created";
        } catch (Exception e) {
            return "Table not crated " + e;
        }
    }
}
