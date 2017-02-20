package io.khasang.moika.model.impl;

import io.khasang.moika.model.MadvDataAcces;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by madv on 20.02.2017.
 */
public class MadvDataAccesImplTest {
    @Autowired
    MadvDataAcces madvDataAcces;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void select() throws Exception {

    }

    @Test
    public void select1() throws Exception {

    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void truncate() throws Exception {
        System.out.println(madvDataAcces.truncate("cars"));
    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void join() throws Exception {

    }

    @Test
    public void internalRequest() throws Exception {

    }

    @Test
    public void caseSelect() throws Exception {

    }

    @Test
    public void backupDB() throws Exception {

    }

}