package com.tgb.Myitem.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tgb.entity.User;

public interface UserDao {

	public User getUser(String id);
	
	public List<User> getAllUser();
	
	public void addUser(User user);
	
	public boolean delUser(String id);
	
	public boolean updateUser(User user);

	public List<User> findByUserName(String userName) throws SQLException;

	public List<User> checkPassAndName(String userName, String password) throws SQLException;

	public int changePass(String password, String userName);

	public Map<String, Object> getUserByPage(int page, int rows);

	public User findUserById(String id) throws SQLException;

	public boolean findByUserName(String id, String userName) throws SQLException;

	public int upPass(String id, String password);

	public int findPass(String id, String password) throws SQLException;

	
}
