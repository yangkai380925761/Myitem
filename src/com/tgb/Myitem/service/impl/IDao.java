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
import org.hibernate.ScrollableResults;
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
import com.tgb.entity.Staff;
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
	 * 查找id 鏌ヨ
	 * 
	 * @param c
	 *            瑕佹煡璇㈢殑瀵硅薄 Class
	 * @param id
	 *            瑕佹煡璇㈢殑id
	 * @return Object
	 */
	public <T> List<T> findByHql(String hql, List<Object> sqlParams) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		for(int i=0;i<sqlParams.size();i++){
			if(sqlParams.get(i) instanceof Integer){
				query.setInteger(i,(Integer)sqlParams.get(i));
			}
			if(sqlParams.get(i) instanceof Boolean){
				query.setBoolean(i,(Boolean)sqlParams.get(i));
			}
			if(sqlParams.get(i) instanceof String){
				query.setString(i,(String)sqlParams.get(i));
			}
		}
		return query.list();
	}
	public <T> List<T> findByids(String hql,String ids) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		query.setString(0,ids);
		return query.list();
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

	public <T> List<T> findInfoByName(String hql, String name) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, name);
		return query.list();
	}

	public <T> List<T> queryByPage(int page, int rows, String sql) {
		Query q = getSession().createQuery(sql);
		return page(q, page, rows);
	}
	public <T> Long count(Class<T> c,String hql) {
		if(hql.equals("")||hql==""||hql==null){
			hql = "from " + c.getSimpleName();
		}
		Query q = getSession().createQuery(hql);
		return getRows(q);
	}
	public long getRows(Query query) {
		ScrollableResults scrollableResults = query.scroll();
		scrollableResults.last();
		if (scrollableResults.getRowNumber() >= 0) {
			return new Long(scrollableResults.getRowNumber() + 1);
		}
		return 0;
	}
	
	
}
