package com.ssf.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssf.model.Product;
import com.ssf.service.ProductService;


@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String VIEW_PATH = "/WEB-INF/";
	
	 ProductService pS=new ProductService();
	 
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//	
		HttpSession session=req.getSession();
		
		int method=Integer.parseInt(req.getParameter("book"));
		ArrayList<Product> list=new ArrayList<Product>();
		if(method==1){
//	
       try {
		list=pS.getProByCate("novel");
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		}else if(method==2){
			try {
				list=pS.getProByCate("teaching");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method==3){
			try {
				list=pS.getProByCate("magzine");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
       
       session.setAttribute("products", list);
      
		req.getRequestDispatcher(VIEW_PATH+"product.jsp").forward(req, resp);
		
			
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
