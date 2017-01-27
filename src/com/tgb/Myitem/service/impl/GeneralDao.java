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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tgb.entity.MenuItem;
import com.tgb.entity.User;

@Repository
public class GeneralDao<T> {
	private Connection conn;
	
	private String className;
	public Connection getConn() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/myitem","root","123456");
	}

	public void setConn(Connection conn) throws SQLException {
		this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myitem","root","123456");
	}
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
	public Serializable save(Object object) throws HibernateException{
		return getSession().save(object);
	}

	
	public List<T>   getAllUser() {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String hql = "from "+className;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}
	
	public List<MenuItem> findAll() throws SQLException{
		String sql="select * from t_menuitem";
		PreparedStatement ps=null;
		ResultSet rs=null;
		MenuItem menu=null;
		ArrayList<MenuItem> v=new ArrayList<MenuItem>();
		Connection con=getConn();
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next()){
			menu=new MenuItem();
			menu.setItemName(rs.getString("itemName"));
			menu.setDescription(rs.getString("description"));
			menu.setCreateTime(new Timestamp(new Date().getTime()));
			v.add(menu);
		}
		
		return v;
	}

	public List<MenuItem> sysMenuItem(int parentItemId, int menuId) throws SQLException{
		String sql="select * from t_menuitem where parentItemId=? and menuId=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		MenuItem menu=null;
		ArrayList<MenuItem> v=new ArrayList<MenuItem>();
		Connection con=getConn();
		ps=con.prepareStatement(sql);
		ps.setInt(1, parentItemId);
		ps.setInt(2, menuId);
		rs=ps.executeQuery();
		while(rs.next()){
			menu=new MenuItem();
			menu.setParentItemId(rs.getInt("parentItemId"));
			menu.setMenuId(rs.getInt("menuId"));
			v.add(menu);
		}
		return v;
	}
	
}
