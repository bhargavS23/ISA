package com.zettamine.isa.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.zettamine.isa.dao.IsaDao;
import com.zettamine.isa.dao.impl.RecruiterDaoImpl;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.Recruiter;

public class RecruiterServices  {
	
	static IsaDao<Recruiter, IsaSearchCriteria> recDao = new RecruiterDaoImpl();
	
	public boolean getBySearchCriteria(Recruiter recruiter){
		
		IsaSearchCriteria scr = new IsaSearchCriteria();
	    scr.setRecrEmail(recruiter.getRecruiterEmail().toLowerCase().trim());
	    scr.setRecrPassword(recruiter.getRecruiterPass().trim());
		List<Recruiter> recrList =recDao.getBySearchCriteria(scr);
		if(recrList.size() > 0) {
			return true;
		}
		else return false;
	}
}
