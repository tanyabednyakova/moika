package io.khasang.moika.dao.impl;

import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.SomeEntityDao;
import io.khasang.moika.entity.SomeEntity;
import io.khasang.moika.entity.SomeSubEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
//@Transactional
public class SomeEntityDaoUnitTest {
    @Autowired
    private SomeEntityDao someEntityDao;

    private List<SomeSubEntity> getTestSubList() {
        List<SomeSubEntity> subEntities = new ArrayList<>();
        SomeSubEntity subEntity = new SomeSubEntity();
        subEntity.setName("Sub one");
        subEntity.setContent("111111111");
        subEntities.add(subEntity);
        subEntity = new SomeSubEntity();
        subEntity.setName("Sub two");
        subEntity.setContent("22222222");
        subEntities.add(subEntity);
        subEntity = new SomeSubEntity();
        subEntity.setName("Sub three");
        subEntity.setContent("3333333");
        subEntities.add(subEntity);
        subEntity = new SomeSubEntity();
        subEntity.setName("Sub four");
        subEntity.setContent("44444444");
        subEntities.add(subEntity);
        return subEntities;
    }

    @Test
    //@Rollback
    //@Transactional
    public void crudTest() {
        SomeEntity someEntity = new SomeEntity();
        someEntity.setName("Asdret");
        someEntity.setInterval(Duration.ofSeconds(10));
        someEntity.setSubEntityList(getTestSubList());

        someEntityDao.create(someEntity);

        Assert.assertNotEquals(0, someEntity.getId());

        SomeEntity resultEntity = someEntityDao.get(someEntity.getId());

        Assert.assertNotNull(resultEntity);

        someEntityDao.delete(resultEntity);
        resultEntity = someEntityDao.get(someEntity.getId());

        Assert.assertNull(resultEntity);

    }
}
