package io.khasang.moika.model;

import org.springframework.jdbc.core.JdbcTemplate;

public class CreateTable {
    private JdbcTemplate jdbcTemplate;

    public CreateTable(JdbcTemplate jdbcTemplate)
    {

        this.jdbcTemplate=jdbcTemplate;
    }

    public CreateTable()
    {
        //this.jdbcTemplate=null;
    }


    public JdbcTemplate getJdbcTemplate() {

        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public String create(){
        try {
           jdbcTemplate.execute("SELECT * FROM temp");

            return "test";

        }catch (Exception e) {
            return "failed "+e;
        }
    }

}
