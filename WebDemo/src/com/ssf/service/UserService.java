package com.ssf.service;

import java.sql.SQLException;

import com.ssf.dao.UserDao;
import com.ssf.model.User;

public class UserService {
   
	UserDao userDao=new UserDao();
	
	public void register(User user)throws UserException{
		//��ݿ����
		if(user.getUsername().equals("wang")){
			throw new UserException("�û������");
		}
	}
	public void login(User user){
		
	}
	
	public User getUserByUsername(String username) throws ClassNotFoundException, SQLException{
		return userDao.getUserByName(username);
	}
}
