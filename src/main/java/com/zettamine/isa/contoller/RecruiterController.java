package com.zettamine.isa.contoller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Session;

import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.Recruiter;
import com.zettamine.isa.dto.SearchCriteria;
import com.zettamine.isa.service.impl.RecruiterServices;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RecruiterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Recruiter recruiter = new Recruiter();
		recruiter.setRecruiterEmail(request.getParameter("email"));
		recruiter.setRecruiterPass(request.getParameter("password"));
		RecruiterServices recrService = new RecruiterServices();
		
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();
		
			if(recrService.verifyRecruiter(recruiter)) {
				rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
				
			}else if(request.getParameter("logout")!=null) {
				
				rd = request.getRequestDispatcher("login.jsp");
			}
			else
			{
				
				rd = request.getRequestDispatcher("login.jsp");
				request.setAttribute("invalid", "Invalid Credentials!! Try Again");
				rd.include(request, response);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
