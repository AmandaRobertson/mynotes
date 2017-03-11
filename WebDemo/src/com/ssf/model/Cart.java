package com.ssf.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	
	
	Map<Integer,Product> cartMap=new HashMap<Integer,Product>();
//	String proName;
	int quantity;
	int total;
    String proName;
    
    int number;
    double price;
	int pid;
	
	


	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Map<Integer, Product> getCartMap() {
		return cartMap;
	}

	public void setCartMap(Map<Integer, Product> cartMap) {
		this.cartMap = cartMap;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void addProToCart(Integer proId,Product product){
		cartMap.put(proId, product);
		
	}
	
	public boolean isProInCart(Integer proId){
		
		return cartMap.containsKey(proId);
		
		
	}
	
	public void incrementQuantiy(String proName){
		
	}
    
	
}
