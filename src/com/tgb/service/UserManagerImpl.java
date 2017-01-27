package com.tgb.service;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tgb.Myitem.service.impl.GeneralDao;
import com.tgb.Myitem.service.impl.UserDao;
import com.tgb.entity.User;
@Repository
public class UserManagerImpl implements UserManager {

	private UserDao userDao;

	

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUser(String id) {
		return userDao.getUser(id);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public boolean delUser(String id) {
		
		return userDao.delUser(id);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public List<User> findByUserName(String userName) {
		// TODO Auto-generated method stub
		List<User> list=null;
		try {
			list= userDao.findByUserName(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<User> checkPassAndName(String userName, String password) {
		List<User> list=null;
		try {
			list=userDao.checkPassAndName(userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int changePass(String password,String userName) {
		int i=userDao.changePass(password,userName);
		if(i==0){
			return 0;
		}else{
			return 1;
		}
		
	}

	@Override
	public Map<String, Object> getUserByPage(int page, int rows) {
		Map<String, Object> map=userDao.getUserByPage(page,rows);
		return map;
	}

	@Override
	public User findUserById(String id) {
		User user=null;
		try {
			user = userDao.findUserById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean findByUserName(String id, String userName) throws SQLException {
		
		return userDao.findByUserName(id,userName);
	}

	@Override
	public int upPass(String id, String password) {
		int i=userDao.upPass(id,password);
		if(i==0){
			return 0;
		}else{
			return 1;
		}
		
	}

	@Override
	public int findPass(String id, String password) {
		int i=0;
		try {
			i = userDao.findPass(id,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i==1){
			return 1;
		}else{
			return 0;
		}
		
	}

}
