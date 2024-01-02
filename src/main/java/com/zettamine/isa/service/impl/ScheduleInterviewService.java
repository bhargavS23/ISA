package com.zettamine.isa.service.impl;

import java.util.List;
import java.util.Optional;

import com.zettamine.isa.dao.impl.ScheduleInterviewDaoImpl;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.ScheduleInterview;

public class ScheduleInterviewService {
	
	static ScheduleInterviewDaoImpl dao = new ScheduleInterviewDaoImpl();
	
	public ScheduleInterview get(Integer id) {
		
		Optional<ScheduleInterview> optional = dao.get(id);
		return optional.get();
	}
	
	public void save(ScheduleInterview interview) {
		dao.save(interview);
	}
	
	public List<ScheduleInterview> getAll(){
		return dao.getAll();
	}
	
	public List<ScheduleInterview> getBySearchCriteria(IsaSearchCriteria criteria){
		return dao.getBySearchCriteria(criteria);
	}
	
	public void update(ScheduleInterview interview) {
		dao.update(interview, "");
	}
	
	public void delete(ScheduleInterview interview) {
		dao.delete(interview);
	}
}