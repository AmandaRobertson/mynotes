package com.ssf.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssf.model.User;

public class UserDao<T> implements Dao<T> {

	static Connection connection = null;
	  
	   void closeConnection() throws SQLException {
			try {
				if (null != connection)
					connection.close();
			} finally {
				connection = null;
				System.gc();	
			}
		}
	  Connection openConnection() throws ClassNotFoundException, SQLException{
		  
		  String driver = "com.mysql.jdbc.Driver";
		  String url    = "jdbc:mysql://127.0.0.1:3306/webdemo";
		  String username = "root";
		  String pwd = "";
		  
		  //1.�����ൽJVM����ȥ
		  Class.forName(driver);
		  //2.DriverManager��ȡ��ݿ�����
		  connection = DriverManager.getConnection(url,username,pwd);
		  return connection;
	  }
	
	@Override
	public void query() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		
	}
	
	public User getUserByName(String username) throws ClassNotFoundException, SQLException{
		Connection conn=openConnection();
    	String sql="select * "+"from sys_user "+"where username=?";
    	PreparedStatement psmt=conn.prepareStatement(sql);
    	psmt.setString(1,username);
          User user=new User();
//    	List<Integer> list=new ArrayList<Integer>();
//        psmt.execute();
        ResultSet rs=psmt.executeQuery();
        while(rs.next()){
        	user.setUsername(rs.getString(1));
        	user.setPassword(rs.getString(2));
        }
    	
    	conn.close();
    	return user;

  
	}



}
