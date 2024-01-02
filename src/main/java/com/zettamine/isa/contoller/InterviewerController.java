package com.zettamine.isa.contoller;

import java.io.IOException;
import java.util.List;

import com.zettamine.isa.dto.Interviewer;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.Interviewer;
import com.zettamine.isa.service.impl.InterviewerServices;
import com.zettamine.isa.service.impl.SkillService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class InterviewerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
String action =request.getParameter("action");
		
		InterviewerServices services = new InterviewerServices();
		    RequestDispatcher rd;
		switch(action) {
		
		case "showall":
			
			SkillService skillServi = new SkillService();
			request.setAttribute("skills", skillServi.getAll());
			rd= request.getRequestDispatcher("interviewer.jsp");
			List<Interviewer> interviewers = services.getAll();
			request.setAttribute("interviewers", interviewers);
			rd.include(request, response);
			break;
			
		case "add-new":
			SkillService skillSer = new SkillService();
			request.setAttribute("skills", skillSer.getAll());
			rd=request.getRequestDispatcher("interviewer-details.jsp");
			rd.include(request, response);
			break;
			
		case "add":
			Interviewer appl = getInterviewer(request);
			
			services.save(appl);
			rd=request.getRequestDispatcher("/interviewer?action=showall");
			rd.include(request, response);
			break;
			
		case "delete":
			Integer id = Integer.parseInt(request.getParameter("id"));
			
			services.delete(services.get(id).get());
			rd=request.getRequestDispatcher("/interviewer?action=showall");
			rd.include(request, response);
			break;
			
		case "edit":
			SkillService skillServ = new SkillService();
			request.setAttribute("skills", skillServ.getAll());
			Integer intrId = Integer.parseInt(request.getParameter("id"));
			Interviewer app=services.get(intrId).get();
			request.setAttribute("interviewer", app);
			rd=request.getRequestDispatcher("interviewer-update-details.jsp");
			rd.include(request, response);
			break;
			
		case "edit-add":
			Interviewer applic = getInterviewer(request);
			applic.setInterviewerId(Integer.parseInt(request.getParameter("id")));
			
			services.update(applic, null);
			rd=request.getRequestDispatcher("/interviewer?action=showall");
			rd.include(request, response);
			
		case "search":
			
			IsaSearchCriteria search = new IsaSearchCriteria();
			SkillService skillService = new SkillService();
			request.setAttribute("skills", skillService.getAll());
			search.setSkillId(Integer.parseInt(request.getParameter("skillId")));
			request.setAttribute("interviewers",services.getBySearchCriteria(search) );
			rd=request.getRequestDispatcher("interviewer.jsp");
			rd.include(request, response);
			break;
			
		case "load":
			SkillService skillServices = new SkillService();
			request.setAttribute("skills", skillServices.getAll());
			rd=request.getRequestDispatcher("interviewer.jsp");
			rd.include(request, response);
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private static Interviewer getInterviewer(HttpServletRequest req) {
		Interviewer inter = new Interviewer();
		inter.setInterviewerEmail(req.getParameter("email"));
		inter.setInterviewerName(req.getParameter("name"));
		inter.setInterviewerSkill(req.getParameter("skill"));
		return inter;
	}

}
