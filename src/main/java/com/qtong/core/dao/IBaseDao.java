package com.qtong.core.dao;

import com.qtong.core.model.Pager;
import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by ZML on 2016/1/15.
 */
public interface IBaseDao {

     Serializable save(Object object);

     void saveOrUpdate(Object entity);

     void update(Object entity);

     void merge(Object entity);

     void deleteObject(Object entity);

     void deleteAll(Collection<?> entities);

     Pager<?> pagedQuery(Criteria criteria, int pageNo, int pageSize);

     List<?> list(Criteria criteria);

     Object get(Class<?> clazz, Serializable id);

     List<?> list(Object entity, String... propertyNames);
}
