package com.ssf.model;

public class Product {
	int proId;
    String proName;
    int price;
    
    String category;
    
    
	public Product() {
		super();
	}
	public Product(String proName, int price) {
		super();
		this.proName = proName;
		this.price = price;
	}
	
	
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int  price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
    
    
}
