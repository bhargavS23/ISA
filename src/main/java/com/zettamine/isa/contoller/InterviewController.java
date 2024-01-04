package com.zettamine.isa.contoller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.zettamine.isa.dto.Applicant;
import com.zettamine.isa.dto.Interviewer;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.ScheduleInterview;
import com.zettamine.isa.dto.Status;
import com.zettamine.isa.service.impl.ApplicantServices;
import com.zettamine.isa.service.impl.InterviewerServices;
import com.zettamine.isa.service.impl.ScheduleInterviewService;
import com.zettamine.isa.service.impl.SkillService;
import com.zettamine.isa.view.dto.InterviewScheduleView;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class InterviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		ScheduleInterviewService service = new ScheduleInterviewService();
		RequestDispatcher rd;
		
		switch(action) {
		case "schedule-new":
			
			ScheduleInterview schedInter = getScheduledInterview(request);
			service.save(schedInter);
			rd=request.getRequestDispatcher("/interview?action=showall");
			rd.include(request, response);
			break;
		case "showall":
			List<InterviewScheduleView> list = service.getAll();
			
			request.setAttribute("interviews", list);
			rd= request.getRequestDispatcher("interviews.jsp");
			rd.include(request, response);
			break;
		case "delete":
			ScheduleInterview schedInterview = new ScheduleInterview();
			schedInterview.setScheduleId(Integer.parseInt(request.getParameter("id")));
			service.delete(schedInterview);
			rd=request.getRequestDispatcher("/interview?action=showall");
			rd.include(request, response);
			break;
		case "edit":
			Integer shedId =Integer.parseInt(request.getParameter("id"));
			ScheduleInterviewService schedIntrSer = new ScheduleInterviewService();
			ApplicantServices applServ = new ApplicantServices();
			IsaSearchCriteria searchCrt = new IsaSearchCriteria();
			InterviewerServices intSer = new InterviewerServices();
			SkillService skillSer = new SkillService();
			
		
			ScheduleInterview schedInterv = schedIntrSer.getScheduledInterview(shedId);
			Integer interviewerId = schedInterv.getInterviewerId();
			Integer applId =schedInterv.getApplicantId();
			Applicant applicant = applServ.get(applId);
			
			request.setAttribute("interviewDetails", schedInterv);
			request.setAttribute("schedule",schedIntrSer.get(shedId));
			InterviewerServices interSer = new InterviewerServices();
			Interviewer interviewer = interSer.get(interviewerId).get();
			
			searchCrt.setSkill_desc(applicant.getApplicantSkill());
			System.out.println("Interviewer Kill " + interviewer.getInterviewerSkill());
			Integer skillId = skillSer.getBySearchCriteria(searchCrt).get(0).getSkillId();
			request.setAttribute("applSkill", applicant.getApplicantSkill());
			request.setAttribute("applicant", applicant);
			searchCrt.setSkill_desc(interviewer.getInterviewerSkill());
			searchCrt.setSkillId(skillId);
			request.setAttribute("interviewers",intSer.getBySearchCriteria(searchCrt) );
			rd = request.getRequestDispatcher("update-schedule.jsp");
			rd.include(request, response);
			break;
		case "update":
			Integer scheId =Integer.parseInt(request.getParameter("id"));
			ScheduleInterview obj = getScheduledInterview(request);
			obj.setScheduleId(scheId);
			service.update(obj);
			rd= request.getRequestDispatcher("/interview?action=showall");
			rd.include(request, response);
			break;
		case "search":
			String applName=null;
              if(!request.getParameter("applName").isBlank()) {
				
            	  applName= request.getParameter("applName");
			}
              String interName=null;
              if(!request.getParameter("interName").isBlank()) {
            	  
            	  interName= request.getParameter("interName");
              }
			
			Date fromDate=null;
			if(!request.getParameter("fromDate").isBlank()) {
				
				fromDate =  Date.valueOf(LocalDate.parse(request.getParameter("fromDate")));
			}
			Date toDate=null;
			if(!request.getParameter("toDate").isBlank())
				toDate =  Date.valueOf(LocalDate.parse(request.getParameter("toDate")));
			
			IsaSearchCriteria criteria = new IsaSearchCriteria();
			criteria.setInterviewerName(interName);
			criteria.setApplicantName(applName);
			criteria.setFromDate(fromDate);
			criteria.setToDate(toDate);
			List<InterviewScheduleView> interviewsList = service.getBySearchCriteria(criteria);
			System.out.println(interviewsList);
			request.setAttribute("interviews", interviewsList);
			rd= request.getRequestDispatcher("interviews.jsp");
			rd.include(request, response);
			break;
			

		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	
	
	static ScheduleInterview getScheduledInterview(HttpServletRequest request) {
		HttpSession s= request.getSession();
		s.setAttribute("recruiterId", 1);
		ScheduleInterview schedInter = new ScheduleInterview();
		schedInter.setApplicantId(Integer.parseInt(request.getParameter("applId")));
		schedInter.setInterviewerId(Integer.parseInt(request.getParameter("interviewerId")));
		String status=request.getParameter("status");
		
		schedInter.setInterviewStatus(Status.valueOf(status.toUpperCase()));
		HttpSession session = request.getSession();
		schedInter.setRecruiterId((Integer)session.getAttribute("recruiterId"));
		//Date date = Date.valueOf(LocalDate.parse(request.getParameter("date")));
		schedInter.setScheduledDate(Date.valueOf(LocalDate.parse(request.getParameter("date"))));
		schedInter.setScheduledTime(Time.valueOf(LocalTime.parse(request.getParameter("time"))));
		System.out.println(schedInter.getScheduledTime());
		
		
		
		
		return schedInter;
		
	}

}
