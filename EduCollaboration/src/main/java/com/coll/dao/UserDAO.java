package com.coll.dao;

import java.util.List;

import com.coll.model.UserDetail;

public interface UserDAO {
	
	public boolean registerUser(UserDetail user);
	
	public boolean updateProfile(UserDetail user);
	
	public UserDetail getUser(String username);
	
	public UserDetail checkUser(String username, String password);
	
	public List<UserDetail> listUsers();
}
