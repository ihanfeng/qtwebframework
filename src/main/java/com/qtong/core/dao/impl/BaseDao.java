package com.qtong.core.dao.impl;

import com.qtong.core.dao.IBaseDao;
import com.qtong.core.utils.Pager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

/**
 * Created by ZML on 2016/1/15.
 */
public abstract class BaseDao implements IBaseDao {

    // 日志输出类
    protected static final Logger LOGGER = Logger.getLogger(BaseDao.class);
    protected HibernateTemplate hibernateTemplate;
    protected SessionFactory sessionFactory;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
/*

 * 注入sessionFactory

*/

    public Session getSession() {

        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = hibernateTemplate.getSessionFactory();
    }

/*

 * 保存PO

*/

    public Serializable save(Object entity) {
        return hibernateTemplate.save(entity);
    }

    /*

     * 保存或更新PO

    */
    public void saveOrUpdate(Object entity) {
        hibernateTemplate.saveOrUpdate(entity);
    }

    /*
     * 更新PO
    */
    public void update(Object entity) {
        hibernateTemplate.update(entity);
    }

    /*
     * 合并PO
    */
    public void merge(Object entity) {
        hibernateTemplate.merge(entity);
    }

    /*
     * 根据id删除PO
    */
    public void delete(Class<?> clazz, Serializable id) {
        hibernateTemplate.delete(this.get(clazz, id));
    }

    public Object get(Class<?> clazz, Serializable id) {
        return hibernateTemplate.get(clazz, id);
    }

    /*
     * 删除PO
    */
    public void deleteObject(Object entity) {
        hibernateTemplate.delete(entity);
    }

    /*
     * 根据id判断PO是否存在
    */
    public boolean exists(Class<?> clazz, Serializable id) {
        return get(clazz, id) != null;
    }

    /*
     * 根据id加载PO
    */
    public Object load(Class<?> clazz, Serializable id) {
        return hibernateTemplate.load(clazz, id);
    }

    /*
     * 获取PO总数(默认为entityClass)
    */
    public int countAll(Class<? extends Object> clazz) {
        Criteria criteria = createCriteria(clazz);
        return Integer.valueOf(criteria.setProjection(Projections.rowCount())
                .uniqueResult().toString());
    }

    /*
     * 根据Criteria查询条件，获取PO总数
    */
    public int countAll(Criteria criteria) {
        return Integer.valueOf(criteria.setProjection(Projections.rowCount())
                .uniqueResult().toString());
    }

    /*
     * 删除所有
    */
    public void deleteAll(Collection<?> entities) {
        if (entities == null)
            return;
        hibernateTemplate.deleteAll(entities);
    }

    /*
     * 获取对象列表根据Criteria
    */
    public List<?> list(Criteria criteria) {
        return criteria.list();
    }

    /*
     * 离线查询
    */
    @SuppressWarnings({"unchecked"})
    public <T> List<T> list(DetachedCriteria criteria) {
        return (List<T>) list(criteria.getExecutableCriteria(getSession()));
    }

    /*
     * 获取全部对象，支持排序
    *
     * @param orderBy
    *
     * @param isAsc
    *
     * @return
    */
    public List<?> list(Class<? extends Object> clazz, String orderBy, boolean isAsc) {
        Criteria criteria = createCriteria(clazz);
        if (isAsc) {
            criteria.addOrder(Order.asc(orderBy));
        } else {
            criteria.addOrder(Order.desc(orderBy));
        }
        return criteria.list();
    }

    /*
     * 按属性查找对象列表，匹配方式为相等
    *
     * @param propertyName
    *
     * @param value
    *
     * @return
    */
    public List<?> list(Class<? extends Object> clazz, String propertyName, Object value) {
        Criteria criteria = createCriteria(clazz);
        Criterion criterion = Restrictions
                .like(propertyName, "%" + value + "%");
        return criteria.add(criterion).list();
    }

    /*
     * 根据查询条件获取数据列表
    */
    @SuppressWarnings("unused")
    private List<?> list(Class<? extends Object> clazz, Criterion criterion) {
        Criteria criteria = createCriteria(clazz);
        criteria.add(criterion);
        return criteria.list();
    }

    /*
     * 按Criteria查询对象列表
    *
     * @param criterions数量可变的Criterion
    *
     * @param criterions
    *
     * @return
    */
    public List<?> list(Class<? extends Object> clazz, Criterion... criterions) {
        return createCriteria(clazz, criterions).list();
    }
/*
 * 按属性查找唯一对象，匹配方式为相等
*
 * @param propertyName
*
 * @param value
*
 * @return
*/

    public Object uniqueResult(Class<? extends Object> clazz, String propertyName, Object value) {
        Criterion criterion = Restrictions.eq(propertyName, value);
        return createCriteria(clazz, criterion).uniqueResult();
    }

    /*
     * 按Criteria查询唯一对象
    *
     * @param criterions数量可变的Criterion
    *
     * @param criterions
    *
     * @return
    */
    public Object uniqueResult(Class<? extends Object> clazz, Criterion... criterions) {
        Criteria criteria = createCriteria(clazz, criterions);
        return criteria.uniqueResult();
    }

    /*
     * 按Criteria查询唯一对象
    *
     * @param criterions
    *
     * @return
    */
    public Object uniqueResult(Criteria criteria) {
        return criteria.uniqueResult();
    }

    /*
     * 为Criteria添加distinct transformer
    *
     * @param criteria
    *
     * @return
    */
// 认为没用
    public Criteria distinct(Criteria criteria) {
// 将结果集进行一次封装，封装成DISTINCT_ROOT_ENTITY对象，方便service层代码使用
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria;
    }

    /*
     * 强制清空session
    */
    public void flush() {
        getSession().flush();
    }

    /*
     * 清空session
    */
    public void clear() {
        getSession().clear();
    }

    /*
     * 创建Criteria实例

    */
    public Criteria createCriteria(Class<? extends Object> entityClass) {
        return getSession().createCriteria(entityClass);
    }

    /*
     * 根据Criterion条件创建Criteria
    *
     * @param criterions数量可变的Criterion
    */
    public Criteria createCriteria(Class<? extends Object> clazz, Criterion... criterions) {

        Criteria criteria = createCriteria(clazz);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

/*
 * 分页查询Criteria
*
 * @param
*
 * @return
*/

    public List<?> findPage(Criteria criteria, int pageNo, int pageSize) {
// 设置起始结果数
        criteria.setFirstResult((pageNo - 1) * pageSize);
// 返回的最大结果集
        criteria.setMaxResults(pageSize);
        return list(criteria);
    }

    /*
     * 分页查询Criteria
    *
     * @param
    *
     * @return
    */
    public Pager<?> pagedQuery(Criteria criteria, int pageNo, int pageSize) {
        Assert.isTrue(pageNo >= 1, "pageNO should start from 1");

// 返回查询结果集
        List<?> list = findPage(criteria, pageNo, pageSize);
/*
 * 注：因为finaPage方法改变了查询条件导致countALL方法查询为空， 所以必须重新设置setFirstResult为0
*/
        criteria.setFirstResult(0);
// count查询
// 获得查询总数
        long totalCount = countAll(criteria);
        if (totalCount < 1) {
            return new Pager<Object>();
        }

// 实际查询返回分页对象
        int startIndex = Pager.getStartOfPage(pageNo, pageSize);
        return new Pager<Object>(startIndex, totalCount, pageSize, list);
    }

    public List<?> list(Object entity, String... propertyNames) {
        Criteria criteria = createCriteria(entity.getClass());
        Class<? extends Object> clazz = entity.getClass();
        for (String prop : propertyNames) {
            String getMethod = "get" + toFirstLetterUpperCase(prop);
            try {
                Object value = clazz.getMethod(getMethod).invoke(entity);

                criteria.add(Restrictions.eqOrIsNull(prop, value));

            } catch (IllegalAccessException e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            } catch (InvocationTargetException e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            } catch (NoSuchMethodException e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            }

        }
        return criteria.list();
    }

    public String toFirstLetterUpperCase(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        String firstLetter = str.substring(0, 1).toUpperCase();
        return firstLetter + str.substring(1, str.length());
    }

}
