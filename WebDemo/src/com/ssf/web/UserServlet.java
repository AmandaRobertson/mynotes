package com.ssf.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssf.model.User;
import com.ssf.service.UserException;
import com.ssf.service.UserService;

public class UserServlet extends HttpServlet{
    UserService userService = new UserService();
	public static final String VIEW_PATH = "/WEB-INF/";   //views/user
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		
		//null
		if("register".equals(method))  //method.equals("register")
		{
			req.getRequestDispatcher(VIEW_PATH+"register.jsp").forward(req, resp);
		}     
		else if("login".equals(method)){
			req.getRequestDispatcher(VIEW_PATH+"login.jsp").forward(req, resp);
		}else if("logout".equals(method)){
		    HttpSession s=req.getSession();
		    s.removeAttribute("susername");
		    req.getRequestDispatcher(VIEW_PATH+"login.jsp").forward(req, resp);;
		}
		
	}
	
	//
	private void doRegister(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//
		User user = new User();
		user.setUsername(username);
		try {
			userService.register(user);
			//
			req.getRequestDispatcher(VIEW_PATH+"welcome.jsp").forward(req, resp);
		} catch (UserException e) {
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher(VIEW_PATH+"register.jsp").forward(req, resp);
		}
	}
	
	private void doLogin(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		HttpSession s=req.getSession();
		s.setAttribute("susername", username);
		//
		req.setAttribute("username", username);
		req.getRequestDispatcher(VIEW_PATH+"welcome.jsp").forward(req, resp);
	}
	//
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		//null
		if("register".equals(method))  //method.equals("register")
		{
			doRegister(req,resp);
		}     
		else if("login".equals(method)){
//			req.getSession().setAttribute("user",null);
			doLogin(req, resp);
		}
	}
}
