package com.zettamine.isa.dao.impl;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.zettamine.isa.dto.Applicant;
import com.zettamine.isa.dto.Interviewer;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.Recruiter;
import com.zettamine.isa.dto.ScheduleInterview;
import com.zettamine.isa.dto.Skill;
import com.zettamine.isa.dto.Status;
import com.zettamine.isa.service.impl.ApplicantServices;
import com.zettamine.isa.service.impl.RecruiterServices;
import com.zettamine.isa.service.impl.SkillService;

public class smaple {

	public static void main(String[] args) {
		
		ApplicantDaoImpl dao = new ApplicantDaoImpl();
//		Applicant applicant = new Applicant(6, "David", "david188@aisa.com", new Skill(6,"Go Lang"),"9900887766", "BSC", "Computer Science", "Good", 78.7);
//		System.out.println(applicant);
//		dao.save(applicant);
//		System.out.println("---".repeat(20));
//		//dao.delete(applicant);
		
		ScheduleInterviewDaoImpl siDao = new ScheduleInterviewDaoImpl();
//		siDao.save(new ScheduleInterview(0, 1, 2, 1, "Krishna", "Rekha", "Santosh", Date.valueOf(LocalDate.of(2024, 1, 4)), Time.valueOf(LocalTime.of(11, 30)),Status.SCHEDULED));
//		System.out.println(siDao.getAll());
//		Optional<ScheduleInterview> optional = siDao.get(8);
//		ScheduleInterview si = optional.get();
//		Applicant applicant = dao.get(13).get();
//		si.setApplicantName(applicant.getApplicantName());
//		si.setApplicantId(applicant.getApplicantId());
//		siDao.delete(si);
//		siDao.update(si, "");
		
		
		//siDao.save(new ScheduleInterview(0, 3, 1, 3, "David", "Gopal", "Kavya", Date.valueOf(LocalDate.of(2024, 1, 10)), Time.valueOf(LocalTime.of(11, 30)),Status.SCHEDULED));
//		IsaSearchCriteria scr = new IsaSearchCriteria();
//		scr.setApplicantId(3);
//		scr.setRecrId(3);
//		scr.setInterviewerId(1);
//		scr.setFromDate(Date.valueOf(LocalDate.of(2024, 1, 4)));
//		scr.setToDate(Date.valueOf(LocalDate.of(2024, 1, 10)));
//		List<ScheduleInterview> list = siDao.getBySearchCriteria(scr);
//		System.out.println(list);
		
//		RecruiterServices rs = new RecruiterServices();
//		boolean bySearchCriteria = rs.getBySearchCriteria(new Recruiter(1, "Santosh", "santosh@gamil.com", "Santosh@1234"));
//		System.out.println(bySearchCriteria);
		
		ApplicantServices app = new ApplicantServices();
//		Applicant applicant = new Applicant(0, "Raju", "raju@outlook.com", new Skill(9,"C++"), "9988654376", "B.COM", "COMPUTERS", "Average", 66.98);
//		Applicant applicant = app.get(1);
//		app.delete(new Applicant(2, null, null, null, null, null, null, null, null));
//		List<Applicant> list = app.getBySearchCriteria(new Applicant(null, null, null, new Skill(1,"java"), null, null, null, null, null));
//		app.save(new Applicant(0, "goutham23 ", " gautham.s@hotmail.com ", new Skill(2,"python"), " 98659865677 ", "B.tech", "ECE", "Good", 65.8));
//		List<Applicant> all = app.getAll();
//		System.out.println(all);
//		List<Applicant> list = app.getBySearchCriteria(applicant);
//		System.out.println(list);
		
		IsaSkillDaoImpl skillDao = new IsaSkillDaoImpl();
		
		SkillService ss = new SkillService();
		
		IsaSearchCriteria src = new IsaSearchCriteria();
		src.setSkill_desc("KOTLIN");
		app.save(new Applicant(0, "SAGAR", "sagar@gmail.com", "KOTLIN","9832774423", "B.TECH", "EEE", "GOOD", 66.6));
		
	}
	
}
