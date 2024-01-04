package com.zettamine.isa.contoller;

import java.io.IOException;
import java.util.List;

import com.zettamine.isa.dao.impl.IsaSkillDaoImpl;
import com.zettamine.isa.dto.Applicant;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.SearchCriteria;
import com.zettamine.isa.service.impl.ApplicantServices;
import com.zettamine.isa.service.impl.InterviewerServices;
import com.zettamine.isa.service.impl.SkillService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ApplicantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action =request.getParameter("action");
		
		ApplicantServices services = new ApplicantServices();
		    RequestDispatcher rd;
		switch(action) {
		
		case "showall":
			SkillService skillServices = new SkillService();
			request.setAttribute("skills", skillServices.getAll());
			rd= request.getRequestDispatcher("applicants.jsp");
			List<Applicant> applicants = services.getAll();
			request.setAttribute("applicants", applicants);
			rd.include(request, response);
			break;
			
		case "add-new":
			SkillService skillSer = new SkillService();
			request.setAttribute("skills", skillSer.getAll());
			rd=request.getRequestDispatcher("app-details.jsp");
			rd.include(request, response);
			break;
			
		case "add":
			Applicant appl = getApplicant(request);
			
			System.out.println(appl.getApplicantSkill());
			
			services.save(appl);
			rd=request.getRequestDispatcher("/applicant?action=showall");
			rd.include(request, response);
			break;
			
		case "delete":
			System.out.println("hello delete");
			Integer id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			
			services.delete(services.get(id));
			rd=request.getRequestDispatcher("/applicant?action=showall");
			rd.include(request, response);
			break;
			
		case "edit":
			SkillService skillServ = new SkillService();
			request.setAttribute("skills", skillServ.getAll());
			Integer appId = Integer.parseInt(request.getParameter("id"));
			Applicant app=services.get(appId);
			request.setAttribute("applicant", app);
			rd=request.getRequestDispatcher("app-update-details.jsp");
			rd.include(request, response);
			break;
			
		case "edit-add":
			Applicant applic = getApplicant(request);
			applic.setApplicantId(Integer.parseInt(request.getParameter("id")));
			services.update(applic);
			rd=request.getRequestDispatcher("/applicant?action=showall");
			rd.include(request, response);
			break;
			
		case "search":
			IsaSearchCriteria srchCrit = new IsaSearchCriteria();
			SkillService skillService = new SkillService();
			request.setAttribute("skills", skillService.getAll());
			srchCrit.setSkillId(Integer.parseInt(request.getParameter("skillId")));
			request.setAttribute("applicants",services.getBySearchCriteria(srchCrit) );
			rd=request.getRequestDispatcher("applicants.jsp");
			rd.include(request, response);
			break;
			
		case "load":
			 skillServices = new SkillService();
			request.setAttribute("skills", skillServices.getAll());
			rd=request.getRequestDispatcher("applicants.jsp");
			rd.include(request, response);
			break;
			
		case "schedule":
			
			System.out.println("Schedule case");
			IsaSearchCriteria searchCrt = new IsaSearchCriteria();
			ApplicantServices appSer = new ApplicantServices();
			IsaSkillDaoImpl skillDao = new IsaSkillDaoImpl();
			Applicant applicant = appSer.get(Integer.parseInt(request.getParameter("id")));
			searchCrt.setSkill_desc(applicant.getApplicantSkill());
			int skillId = skillDao.getBySearchCriteria(searchCrt).get(0).getSkillId();
			searchCrt.setSkillId(skillId);
			InterviewerServices intSer = new InterviewerServices();
			request.setAttribute("interviewers",intSer.getBySearchCriteria(searchCrt) );
			request.setAttribute("applicant", applicant);
			rd=request.getRequestDispatcher("schedule-interviews.jsp");
			rd.include(request, response);
			break;
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	static Applicant getApplicant(HttpServletRequest req) {
		Applicant appl = new Applicant();
		appl.setApplicantName(req.getParameter("name"));
		appl.setApplicantEmail(req.getParameter("email"));
		appl.setApplicantSkill(req.getParameter("skill"));
		appl.setApplicantPhNo(req.getParameter("phone"));
		appl.setStream(req.getParameter("stream"));
		appl.setApplicantQualification(req.getParameter("qualification"));
		appl.setApplicantRemarks(req.getParameter("remarks"));
		if(req.getParameter("percentage")!=null)
		appl.setApplicantAggrePercentage( Double.parseDouble(req.getParameter("percentage")));
		
		return appl;
	}

}
