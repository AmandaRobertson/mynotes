package com.ssf.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssf.model.Cart;
import com.ssf.model.Product;
import com.ssf.service.CartService;
import com.ssf.service.ProductService;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
   
		ProductService pS=new ProductService();
		CartService cS=new CartService();
		cS.deletePro("java");
//		Product pro=pS.getProById(1);
//		System.out.println(pro.getCategory()+pro.getProName());
//		System.out.println(cS.isProInCart(pro));
////		cS.addProToCart(pro);
//		
//		List<Cart> list=new ArrayList<Cart>();
//		list=cS.getCart();
//		for(Cart cart:list){
//			System.out.println(cart.getProName());
//		}
//		
//		int q=cS.getQuantity(pro);
//		System.out.println(q);
//		cS.incrementQuantity(pro,1,20);
		
//		ArrayList<Product> list=new ArrayList<Product>();
//		try {
//			list=pS.getProByCate("novel");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(Product pro:list){
//			System.out.println(pro.getProName());
//			System.out.println(pro.getPrice());
//			
//		}
////		System.out.println("muyoua");
	}

}
