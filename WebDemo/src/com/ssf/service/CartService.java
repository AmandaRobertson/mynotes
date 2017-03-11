package com.ssf.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssf.dao.CartDao;
import com.ssf.model.Cart;
import com.ssf.model.Product;

public class CartService {
     CartDao cartDao=new CartDao();
     
     public ArrayList getCart() throws ClassNotFoundException, SQLException{
    	return cartDao.getCart();
     }
     public void deleteCart() throws ClassNotFoundException, SQLException{
    	 cartDao.deleteCart();
     }

	public ArrayList getProByName(String proName) throws SQLException, ClassNotFoundException{
    return  cartDao.getPro(proName); 
	}
	public void deletePro(String proName) throws ClassNotFoundException, SQLException{
		cartDao.deletePro(proName);
	}
	
	public int countTotal() throws ClassNotFoundException, SQLException{
		ArrayList<Cart> list=cartDao.getCart();
		int total=0;
		for(Cart cart:list){
			int a=cart.getTotal();
			total+=a;			
		}
		return total;
	}
	public void updateQuantity(String proName,int quantity) throws ClassNotFoundException, SQLException{
		cartDao.updateQuantity(proName, quantity);
	}
	
   public void incrementQuantity(Product pro,int quantity,int total) throws ClassNotFoundException, SQLException{
      cartDao.incrementQuantity(pro,quantity,total);
   }
   public int getQuantity(Product pro) throws ClassNotFoundException, SQLException{
	   return cartDao.getQuantiy(pro);
   }
   
   public int getTotal(Product pro) throws ClassNotFoundException, SQLException{
	   return cartDao.getTotal(pro);
   }
	
	public void addProToCart(Product pro) throws ClassNotFoundException, SQLException{
     cartDao.addProToCart(pro);
	}
	
	public boolean isProInCart(Product pro) throws ClassNotFoundException, SQLException{
		List<String> list=new ArrayList<String>();
		list=cartDao.getPro(pro.getProName());
		
		boolean b;
//		list=cartDao.getProByName(pro.getProName())
		if(list.contains(pro.getProName())){
			b=true;
		}else{
			b=false;
		}	
		return b;
		
	}
}
