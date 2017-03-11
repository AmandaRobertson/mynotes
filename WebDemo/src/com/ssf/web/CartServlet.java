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
import javax.servlet.http.HttpSession;

import com.ssf.model.Cart;
import com.ssf.model.Product;
import com.ssf.service.CartService;
import com.ssf.service.ProductService;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String VIEW_PATH = "/WEB-INF/";
	
	ProductService pS=new ProductService();
	CartService cS=new CartService();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int proId=Integer.parseInt(req.getParameter("proId"));
		HttpSession session=req.getSession();
		Product pro=new Product();
			try {
				pro=pS.getProById(proId);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			String proName=pro.getProName();
         try {
			if(cS.isProInCart(pro)){
				int quantity = 0;
				int total = 0;
				try {
					quantity = cS.getQuantity(pro)+1;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					total = cS.getTotal(pro)+pro.getPrice();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					cS.incrementQuantity(pro,quantity,total);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			 }else{
				 try {
					cS.addProToCart(pro);
//					cS.incrementQuantity(pro, 1, pro.getPrice());
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//	     List<Cart> list=new ArrayList<Cart>();
	     List<Cart> list=new ArrayList<Cart>();
         try {
			list=cS.getCart();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
         int total=0;
         try {
			total=cS.countTotal();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
         session.setAttribute("total", total);
         session.setAttribute("cartList", list);
//         session.setAttribute(arg0, arg1);
         req.getRequestDispatcher(VIEW_PATH+"cart.jsp").forward(req, resp);
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=req.getParameter("action");
		String proName=req.getParameter("proName");
		if(action.equals("clean")){
		try {
			cS.deleteCart();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else if(action.equals("delete")){
			try {
				cS.deletePro(proName);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		req.getRequestDispatcher(VIEW_PATH+"cart.jsp").forward(req, resp);
	}
	 

}
