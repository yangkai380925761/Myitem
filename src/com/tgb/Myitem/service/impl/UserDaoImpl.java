package com.tgb.Myitem.service.impl;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.metamodel.source.annotations.HibernateDotNames;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tgb.entity.User;
import com.tgb.utils.MD5Util;

public class UserDaoImpl implements UserDao {
	//private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public User getUser(String id) {
		
		String hql = "from User u where u.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		
		return (User)query.uniqueResult();
	}

	@Override
	public List<User> getAllUser() {
		
		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Override
	public void addUser(User user) {
		String ps=MD5Util.md5(user.getPassword());
		user.setPassword(ps);
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public boolean delUser(String id) {
		
		String hql = "delete User u where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		
		return (query.executeUpdate() > 0);
	}

	@Override
	public boolean updateUser(User user) {
		
		String hql = "update User u set u.userName = ?,u.userTrueName=?,u.email=?,u.phone=?,u.userType=?,u.quanxianNum=?,u.quanxianMenu=? where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, user.getUserName());
		query.setString(1, user.getUserTrueName());
		query.setString(2, user.getEmail());
		query.setString(3, user.getPhone());
		query.setString(4, user.getUserType());
		query.setString(5, user.getQuanxianNum());
		query.setString(6, user.getQuanxianMenu());
		query.setString(7, user.getId());
		return (query.executeUpdate() > 0);
	}

	@Override
	public List<User> findByUserName(String userName) throws SQLException {
		String hql="select * from t_user u where u.userName=?";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet r=null;
		User x=null;
		ArrayList v=null;
		if(conn==null){
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/myitem","root","123456");
		}
		ps=conn.prepareStatement(hql);
		ps.setString(1, userName);
		int i=0;
		v=new ArrayList();
		r=ps.executeQuery();
		while (r.next()) {
        	 x=new User();
	            x.setUserName(r.getString("userName"));
	            x.setPassword(r.getString("password"));
	            x.setQuanxianMenu(r.getString("quanxianMenu"));
	            x.setQuanxianNum(r.getString("quanxianNum"));
	            x.setEmail(r.getString("Email"));
	            x.setPhone(r.getString("phone"));
	            x.setUserTrueName(r.getString("userTrueName"));
	            x.setUserType(r.getString("userType"));
	            x.setLoginTime(new Timestamp(new Date().getTime()));
            v.add(x);
            i++;
         }
		if(i==0){
			v=null;
		}
		return v;
	}

	@Override
	public List<User> checkPassAndName(String userName, String password) throws SQLException{
		String sql="select * from t_user where userName=? and password=?";
		String sql1="update User u set u.loginTime=? where u.userName=?";
		PreparedStatement ps=null;
		Connection conn=null;
		ResultSet rs=null;
		User x=null;
		ArrayList<User> v=null;
		if(conn==null){
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/myitem","root","123456");
		}
		ps=conn.prepareStatement(sql);
		ps.setString(1, userName);
		ps.setString(2, password);
		v=new ArrayList<User>();
		rs=ps.executeQuery();
		while(rs.next()){
			x=new User();
			x.setUserName(userName);
			x.setPassword(password);
			x.setLoginTime(new Timestamp(new Date().getTime()));
			v.add(x);
		}
		Query query = sessionFactory.getCurrentSession().createQuery(sql1);
		query.setDate(0, new Timestamp(new Date().getTime()));
		query.setString(1, userName);
		query.executeUpdate();
		return v;
	}

	@Override
	public int changePass(String password, String userName) {
		String sql="update User u set u.password=? where u.userName=?";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setString(0, MD5Util.md5(password));
		query.setString(1, userName);
		if(query.executeUpdate() > 0){
			return 1;
		}else{
			return 0;
		}
		
	}

	public Map<String, Object> getUserByPage(int page, int rows) {
		Map<String, Object> map=new HashMap<String, Object>();
		String hql = "from User";
		Long total=(long) 0;
		Query query=null;
		query = sessionFactory.getCurrentSession().createQuery(hql);
		ScrollableResults scrollableResults = query.scroll();
		scrollableResults.last();
		if (scrollableResults.getRowNumber() >= 0) {
			  total=new Long(scrollableResults.getRowNumber() + 1);
		}else{
			
		}
		query = sessionFactory.getCurrentSession().createQuery(hql).setFirstResult((page-1)*rows).setMaxResults(rows);
		List<User> userList=query.list();
		map.put("rows", userList);
		map.put("total", total);
		return map;
		
	}

	@Override
	public User findUserById(String id) throws SQLException {
		String hql = "from User where id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		User user=(User) query.list().get(0);
		if(user!=null){
			return user;
		}
		return null;
	}

	@Override
	public boolean findByUserName(String id, String userName) throws SQLException {
		User user=new User();
		if(id==null||id.trim().length()==0){
			List<User> users=findByUserName(userName);
			if(users!=null){
				return true;
			}else{
				return false;
			}
		}
		
		user=findUserById(id);
		if(userName.equals(user.getUserName())){
			return false;
		}
		
		List<User> users=findByUserName(userName);
		if(users!=null){
			return true;
		}else{
			return false;
		}
	}

	public int upPass(String id, String password) {
		String hql="update User u set u.password=? where u.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, MD5Util.md5(password));
		query.setString(1, id);
		if(query.executeUpdate() > 0){
			return 1;
		}else{
			return 0;
		}
		
	}

	@Override
	public int findPass(String id, String password) throws SQLException {
		User user=new User();
		user=findUserById(id);
		
		if(MD5Util.md5(password).equals(user.getPassword())){
			return 1;
		}
		return 0;
	}

}
