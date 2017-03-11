package com.ssf.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssf.model.Cart;
import com.ssf.model.Product;

public class CartDao implements Dao {

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
	public void delete(){
		// TODO Auto-generated method stub
	}
	
	public void deleteCart() throws ClassNotFoundException, SQLException{
		Connection conn=openConnection();
    	String sql="delete from sys_cart";
    	PreparedStatement psmt=conn.prepareStatement(sql);
    	
        psmt.execute();
    	closeConnection();
	}
	
	public ArrayList getCart() throws ClassNotFoundException, SQLException{
		Connection conn=openConnection();
    	String sql="select * "+"from sys_cart ";
    	PreparedStatement psmt=conn.prepareStatement(sql);
//    	psmt.setString(1,pro.getProName());
    
//    	List<Integer> list=new ArrayList<Integer>();
//        psmt.execute();
        ResultSet rs=psmt.executeQuery();

        List<Cart> list=new ArrayList<Cart>();
        while(rs.next()){
        	Cart cart=new Cart();
        	cart.setProName(rs.getString(1));
        	cart.setQuantity(rs.getInt(2));
        	cart.setTotal(rs.getInt(3));
        	list.add(cart);
        }
    	closeConnection();
    	return (ArrayList) list;
	}
	
	public void updateQuantity(String proName,int quantity) throws ClassNotFoundException, SQLException{
		Connection conn=openConnection();
//    	String sql="insert into sys_cart(quantity) "+"values(?)"+"where proName=
    	String sql="update sys_cart "+"set quantity=? where proName=?";
    	PreparedStatement psmt=conn.prepareStatement(sql);
    	psmt.setInt(1,quantity);
    	psmt.setString(2, proName);
        psmt.executeUpdate();
    	closeConnection();
	}

	
	public ArrayList getPro(String proName) throws SQLException, ClassNotFoundException{
		Connection conn=openConnection();
//    	String sql="insert into sys_cart(quantity) "+"values(?)"+"where proName=
    	String sql="select proName from sys_cart";
    	PreparedStatement psmt=conn.prepareStatement(sql);
    	List<String> list=new ArrayList<String>();
    	ResultSet rs=psmt.executeQuery();
    	while(rs.next()){
    		list.add(rs.getString(1));   		
    	}
//    	psmt.setString(1,pro.getProName());
//    	psmt.setString(2, pro.getProName());

    	closeConnection();
    	return (ArrayList) list;
	}
	
   public void incrementQuantity(Product pro,int quantity,int total) throws ClassNotFoundException, SQLException{
		Connection conn=openConnection();
    	String sql="update sys_cart "+"set quantity=?,total=?"+" where proName=?";
    	PreparedStatement psmt=conn.prepareStatement(sql);
    	psmt.setInt(1,quantity);
    	psmt.setInt(2, total);
    	psmt.setString(3, pro.getProName());
        psmt.execute();
    	closeConnection();
   }
	
	public void addProToCart(Product pro) throws ClassNotFoundException, SQLException{
		Connection conn=openConnection();
    	String sql="insert into sys_cart(proName,quantity,total) "+"values(?,1,?)";
    	PreparedStatement psmt=conn.prepareStatement(sql);
    	psmt.setString(1,pro.getProName());
    	psmt.setInt(2,pro.getPrice());
    	
        psmt.execute();
    	closeConnection();

	}
	public int getQuantiy(Product pro) throws ClassNotFoundException, SQLException{
		Connection conn=openConnection();
    	String sql="select quantity from sys_cart "+" where proName=?";
    	PreparedStatement psmt=conn.prepareStatement(sql);
    	psmt.setString(1,pro.getProName());
//    	List<Integer> list=new ArrayList<Integer>();
//        psmt.execute();
       ResultSet rs=psmt.executeQuery();
       int quantity=0;
        while(rs.next()){
        	quantity=rs.getInt(1);
        }
    	closeConnection();
    	return quantity;
	}
	
	public int getTotal(Product pro) throws ClassNotFoundException, SQLException{
		Connection conn=openConnection();
    	String sql="select total "+"from sys_cart "+"where proName=?";
    	PreparedStatement psmt=conn.prepareStatement(sql);
    	psmt.setString(1,pro.getProName());
//    	List<Integer> list=new ArrayList<Integer>();
//        psmt.execute();
        ResultSet rs=psmt.executeQuery();
        int total=0;
        while(rs.next()){
        	total=rs.getInt(1);
        }
    	closeConnection();
    	return total;
	}
	
	public void deletePro(String proName) throws ClassNotFoundException, SQLException{
		Connection conn=openConnection();
		
    	String sql="delete from sys_cart"+" where proName=?";
    	PreparedStatement psmt=conn.prepareStatement(sql);
    	psmt.setString(1,proName);
    	
        psmt.execute();
    	closeConnection();
	}

}
