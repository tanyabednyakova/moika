package io.khasang.moika.model;

import org.springframework.jdbc.core.JdbcTemplate;

public class CreateNilTable {
    private JdbcTemplate jdbcTemplate;

    public CreateNilTable() {

    }

    public CreateNilTable(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String createN() {
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS \"Test\"");
            jdbcTemplate.execute("CREATE TABLE public.\"Test\" (\n" +
                    "    code        char(5) CONSTRAINT firstkey PRIMARY KEY,\n" +
                    "    title       varchar(40) NOT NULL,\n" +
                    "    did         integer NOT NULL,\n" +
                    "    date_prod   date,\n" +
                    "    kind        varchar(10),\n" +
                    "    len         interval hour to minute\n" +
                    ");");
            return "Table created";
        } catch (Exception e) {
            return "Ощипка!" + e;
        }
    }

    public String createNilTableStatus() {
        return createN();
    }

}
