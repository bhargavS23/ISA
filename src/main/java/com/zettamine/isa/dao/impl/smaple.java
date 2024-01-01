package com.zettamine.isa.dao.impl;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.zettamine.isa.dto.Applicant;
import com.zettamine.isa.dto.Interviewer;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.Recruiter;
import com.zettamine.isa.dto.ScheduleInterview;
import com.zettamine.isa.dto.Skill;
import com.zettamine.isa.dto.Status;
import com.zettamine.isa.service.impl.RecruiterServices;

public class smaple {

	public static void main(String[] args) {
		
//		ApplicantDaoImpl dao = new ApplicantDaoImpl();
//		Applicant applicant = new Applicant(6, "David", "david188@aisa.com", new Skill(6,"Go Lang"),"9900887766", "BSC", "Computer Science", "Good", 78.7);
//		System.out.println(applicant);
//		dao.save(applicant);
//		System.out.println("---".repeat(20));
//		//dao.delete(applicant);
		
//		ScheduleInterviewDaoImpl siDao = new ScheduleInterviewDaoImpl();
//		siDao.save(new ScheduleInterview(0, 1, 2, 1, "Krishna", "Rekha", "Santosh", Date.valueOf(LocalDate.of(2024, 1, 4)), Time.valueOf(LocalTime.of(11, 30)),Status.SCHEDULED));
//		System.out.println(siDao.getAll());
		
		RecruiterServices rs = new RecruiterServices();
		boolean bySearchCriteria = rs.getBySearchCriteria(new Recruiter(1, "Santosh", "santosh@gamil.com", "Santosh@1234"));
		System.out.println(bySearchCriteria);
		
	}
	
}
