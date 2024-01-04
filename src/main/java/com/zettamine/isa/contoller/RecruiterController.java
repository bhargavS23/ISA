package com.zettamine.isa.contoller;

import java.io.IOException;


import com.zettamine.isa.dto.Recruiter;
import com.zettamine.isa.service.impl.RecruiterServices;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class RecruiterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email=request.getParameter("email");
		String pass = request.getParameter("password");
		RecruiterServices service = new RecruiterServices();
		Recruiter recr = new  Recruiter();
		
		
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		if(request.getParameter("logout")!=null) {
			//System.out.println("If case logout");
			session.removeAttribute("username");
			rd= request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		recr.setRecruiterEmail(email);
		recr.setRecruiterPass(pass);
			if(service.verifyRecruiter(recr)) {
				
				session.setAttribute("email", email);
				//System.out.println("verified");
					session.setAttribute("username", "user1");
					rd= request.getRequestDispatcher("index.jsp");
					rd.include(request, response);
				}
			else 
			{
				//System.out.println("Else not verified");
				rd = request.getRequestDispatcher("login.jsp");
				request.setAttribute("invalid", "Invalid Credentials!! Try Again");
				rd.include(request, response);
			}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
