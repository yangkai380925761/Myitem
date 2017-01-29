package com.tgb.Myitem.service.impl;

import java.io.Serializable;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tgb.entity.MenuItem;
import com.tgb.entity.Role;
import com.tgb.entity.Standard;
import com.tgb.entity.User;
/**
 * 通过的dao层结构
 * @author 杨凯
 *
 * @param <T>
 */

@Repository
public class IDao<T>{
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Transaction beginTran(){
		return getSession().beginTransaction();
	}
	
	public void commit(){
		getSession().getTransaction()
					.commit();
	}
	/**
	 * 获取所有的数据
	 * @param hql
	 * @return
	 */
	public <T> List<T> findAll(String hql) {
		Query q = getSession().createQuery(hql);
		return q.list();
	}
	/**
	 * 修改的方法
	 * @param object
	 */
	public void update(Object object) {
		getSession().update(object);
		Transaction tran=getSession().beginTransaction(); 
		getSession().flush();
		tran.commit();
	}
	/**
	 * 带有分页获取数据的方法
	 */
	private static<T> List<T> page(Query query, int page, int rows){
		if (page > 1) {
			page = (page - 1) * rows;
		} else {
			page = 0;
		}
		query.setFirstResult(page);
		query.setMaxResults(rows);
		return query.list();
	}
	public <T> List<T> findByPage(String hql, int page, int rows) {
		Query q = getSession().createQuery(hql);
		return page(q, page, rows);
	}
	public Long count(String hql) {
		Query query = getSession().createQuery(hql);
		List<Long> list = query.list();
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}
	  public void deleteById(Class c, String id)
	   {
	       String hql = "delete  "+c.getName()+" s where s.id=?";
	       Query query = getSession().createQuery(hql);
	       query.setString(0, id);
	       query.executeUpdate();
	   }

	private Object getEntityIdName(Class c) {
		return getSessionFactory().getClassMetadata(c).getIdentifierPropertyName();
	}

	public void add(Object object) {
		// TODO Auto-generated method stub
		getSession().save(object);
		Transaction tran=getSession().beginTransaction(); 
		getSession().flush();
		tran.commit();
	}
}
