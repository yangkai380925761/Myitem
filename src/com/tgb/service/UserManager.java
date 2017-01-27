package com.tgb.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tgb.entity.User;

public interface UserManager {

	public User getUser(String id);
	
	public List<User> getAllUser();
	
	public void addUser(User user);
	
	public boolean delUser(String id);
	
	public boolean updateUser(User user);

	public List<User> findByUserName(String userName);

	public List<User> checkPassAndName(String userName, String password);

	public int changePass(String password, String userName);

	public Map<String, Object> getUserByPage(int page, int rows);

	User findUserById(String id);

	public boolean findByUserName(String id, String userName) throws SQLException;

	public int upPass(String id, String password);

	public int findPass(String id, String password);
}
