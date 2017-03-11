package com.ssf.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssf.dao.ProductDao;
import com.ssf.model.Product;

public class ProductService {
 
	public ProductDao productDao=new ProductDao();
    ArrayList query(){
    	return null;
    }
    public ArrayList getProByCate(String category) throws ClassNotFoundException, SQLException{
    	ArrayList<Product> list=productDao.getProByCate(category);
//    	Map<> 
    	return  list;
    }
    public Product getProById(int id) throws ClassNotFoundException, SQLException{
    	return productDao.getProById(id);
    }
}
