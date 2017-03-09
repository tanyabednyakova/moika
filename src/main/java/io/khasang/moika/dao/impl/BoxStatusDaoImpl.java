package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.BoxStatusDao;
import io.khasang.moika.entity.BoxStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Transactional
@Repository("boxStatusDao")
public class BoxStatusDaoImpl  extends AllStatusDaoImpl<BoxStatus>  implements BoxStatusDao {

    public BoxStatusDaoImpl() {
    }

}
