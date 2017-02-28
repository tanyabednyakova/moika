package io.khasang.moika.model;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TatyanaDataAccessImp implements TatyanaDataAccess {


    private JdbcTemplate jdbcTemplate;

    public TatyanaDataAccessImp(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }

    public TatyanaDataAccessImp(){
        //this.jdbcTemplate=null;
    }

    public JdbcTemplate getJdbcTemplate(){
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String selectIn() {
        try {
            //jdbcTemplate.execute("SELECT * FROM temp");
            List s=jdbcTemplate.queryForList("SELECT  * FROM temp");
            String str="";
            for (Object t:s) {
                str=str+t;
                
            }
            return str;

        }catch (Exception e) {
            return "failed "+e;
        }
    }


}
