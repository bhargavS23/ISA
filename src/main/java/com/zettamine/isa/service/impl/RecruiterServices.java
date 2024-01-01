package com.zettamine.isa.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.zettamine.isa.dao.IsaDao;
import com.zettamine.isa.dao.impl.RecruiterDaoImpl;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.Recruiter;
import com.zettamine.isa.validation.ValidateCredentials;

public class RecruiterServices  {
	
	static IsaDao<Recruiter, IsaSearchCriteria> recDao = new RecruiterDaoImpl();
	
	public boolean getBySearchCriteria(Recruiter recruiter) {
		if (validate(recruiter)) {
			IsaSearchCriteria scr = new IsaSearchCriteria();
			scr.setRecrEmail(recruiter.getRecruiterEmail());
			scr.setRecrPassword(recruiter.getRecruiterPass().trim());
			List<Recruiter> recrList = recDao.getBySearchCriteria(scr);
			if (recrList.size() > 0) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean validate(Recruiter recruiter) {
		
		ValidateCredentials validate = new ValidateCredentials();
		recruiter.setRecruiterEmail(recruiter.getRecruiterEmail().trim().toLowerCase());
		if(validate.validateEmail(recruiter.getRecruiterEmail())) {
			return true;
		}
		return false;
	}
}
