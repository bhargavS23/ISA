package com.zettamine.isa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zettamine.isa.dao.IsaDao;
import com.zettamine.isa.dao.impl.ApplicantDaoImpl;
import com.zettamine.isa.dto.Applicant;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.validation.ValidateCredentials;

public class ApplicantServices {
	
	static IsaDao<Applicant, IsaSearchCriteria> appDao = new ApplicantDaoImpl();
	static ValidateCredentials validate = new ValidateCredentials();
	
	public Applicant get(Integer id) {
		
		Optional<Applicant> appOpt = appDao.get(id);
		if(appOpt.isPresent()) {
			return appOpt.get();
		}
		else return appOpt.orElse(new Applicant());
	}
	
	public List<Applicant> getAll(){
		return appDao.getAll();
	}
	
	public void save(Applicant app) {
	
		if(validate(app)) {
			appDao.save(app);
		}
	}
	
	public void update(Applicant app) {
		if (validate(app)) {
			appDao.update(app, "");
		}
	}
	public void delete(Applicant app) {
		appDao.delete(app);
	}
	 
	public List<Applicant> getBySearchCriteria(IsaSearchCriteria src) {
		
		List<Applicant> byScrList = new ArrayList<>();
			byScrList = appDao.getBySearchCriteria(src);
			return byScrList;
	}

	private static boolean validate(Applicant app) {

		// trim the leading and trailing white spaces if present
		// replace extra white spaces with single white space
		app.setApplicantName(app.getApplicantName().toUpperCase().trim().replaceAll("\\s+", " "));
		app.setApplicantEmail(app.getApplicantEmail().trim());
		app.setApplicantPhNo(app.getApplicantPhNo().trim());

		if (validate.validateName(app.getApplicantName())) {
			if (validate.validateEmail(app.getApplicantEmail())) {
				if (validate.validatePhoneNum(app.getApplicantPhNo())) {
					return true;
				}
			}
		}
		return false;
	}
}
