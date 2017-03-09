package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.BoxStatusDao;
import io.khasang.moika.dao.BoxTypeDao;
import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository("boxTypeDao")
public class BoxTypeDaoImpl extends AllTypeDaoImpl<BoxType>  implements BoxTypeDao {

    public BoxTypeDaoImpl() {
    }

}
