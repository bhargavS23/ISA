package com.zettamine.isa.contoller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.Recruiter;
import com.zettamine.isa.dto.SearchCriteria;
import com.zettamine.isa.service.IsaService;
import com.zettamine.isa.service.impl.RecruiterServices;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RecruiterController
 */
public class RecruiterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruiterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email=request.getParameter("email");
		String pass = request.getParameter("password");
		IsaService<Recruiter,SearchCriteria> dao = new RecruiterServices();
		IsaSearchCriteria scr = new IsaSearchCriteria();
	    scr.setRecrEmail(email);
	    scr.setRecrPassword(pass);
		List<Recruiter> recrList =dao.getBySearchCriteria(scr);
		
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();
		
			if(recrList.size()>0) {
				System.out.println("if case");
				rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
				
			}else
			{
				System.out.println("Else case");
				
				rd = request.getRequestDispatcher("login.jsp");
				
				
				request.setAttribute("invalid", "Invalid Credentials!! Try Again");
				rd.include(request, response);
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
