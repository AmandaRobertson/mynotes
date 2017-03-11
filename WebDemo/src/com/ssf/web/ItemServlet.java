package com.ssf.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssf.model.Cart;
import com.ssf.service.CartService;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String VIEW_PATH = "/WEB-INF/";
    CartService cS=new CartService();
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String proName=(String) req.getParameter("proName");
//	    String proName=req.getp
	    try {
			cS.deletePro(proName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	    List<Cart> list=new ArrayList<Cart>();
//		try {
//			list = cS.getCart();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    req.setAttribute("cartList", list);
//	    
//	    req.getRequestDispatcher(VIEW_PATH+"cart.jsp").forward(req, resp);
	    this.doUpdate(req, resp);
		
	}
	public void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		 List<Cart> list=new ArrayList<Cart>();
			try {
				list = cS.getCart();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    req.setAttribute("cartList", list);
		    
		    req.getRequestDispatcher(VIEW_PATH+"cart.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String upd=req.getParameter("upd");
		if(upd.equals("clear")){
		try {
			cS.deleteCart();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("total", 0);
		}else if(upd.equals("update")){
		   String[] quantity=req.getParameterValues("quantity");
		   String[] product=req.getParameterValues("product");
		  ArrayList<Cart> list=null;
		  try {
			list=cS.getCart();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  int i=0;
		   for(Cart c:list){
           c.setQuantity(Integer.parseInt(quantity[i]));
           i++;
		   }
			   
		}
//		}else if(action.equals("delete")){
//			try {
//				cS.deletePro(proName);
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		this.doUpdate(req, resp);
	}

}
