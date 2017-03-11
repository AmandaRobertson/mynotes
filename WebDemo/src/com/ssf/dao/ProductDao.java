package com.ssf.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ssf.model.Product;


public class ProductDao<T> implements Dao<T> {
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
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
    
    public ArrayList<T> getProByCate(String category) throws ClassNotFoundException, SQLException{
//    	=JDBCUtils.getInstance().queryt(sql, T);
    	Connection conn=openConnection();
    	String sql="select * from sys_product "+"where category=?";
    	PreparedStatement psmt=conn.prepareStatement(sql);
    	psmt.setString(1, category);
    	ResultSet rs=psmt.executeQuery();
    	ArrayList<Product> list=new ArrayList<Product>();
    	while(rs.next()){
    		Product pro=new Product();
    		pro.setProId(rs.getInt(1));
    	    pro.setProName(rs.getString(2));
    		pro.setPrice(rs.getInt(3));
    		pro.setCategory(category);
    		list.add(pro);
    	}
    	closeConnection();
    	return (ArrayList<T>) list;
    }
    
    public Product getProById(int id) throws SQLException, ClassNotFoundException{
    	Connection conn=openConnection();
    	String sql="select * from sys_product "+"where proId=?";
    	PreparedStatement psmt=conn.prepareStatement(sql);
    	psmt.setInt(1, id);
    	ResultSet rs=psmt.executeQuery();
    	Product pro=new Product();
    	while(rs.next()){
    		pro.setProName(rs.getString(2));
    		pro.setPrice(rs.getInt(3));
    		pro.setCategory(rs.getString(4));
    	}
    	closeConnection();
    	return pro;
    }
    
    
	@Override
	public void query() {
		// TODO Auto-generated method stub
		
	}
}
