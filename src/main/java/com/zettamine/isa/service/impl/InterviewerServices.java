package com.zettamine.isa.service.impl;

import java.util.List;
import java.util.Optional;

import com.zettamine.isa.dao.impl.InterviewerDaoImpl;
import com.zettamine.isa.dto.Interviewer;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.validation.ValidateCredentials;

public class InterviewerServices {
	
	InterviewerDaoImpl intrwDao =null;
	static ValidateCredentials validate = new ValidateCredentials();
	public InterviewerServices() {
		intrwDao = new InterviewerDaoImpl();
	}

	public Optional<Interviewer> get(int id) {
		return intrwDao.get(id);
	}

	public List<Interviewer> getAll() {
		return intrwDao.getAll();
	}

	public List<Interviewer> getBySearchCriteria(IsaSearchCriteria criteria) {
		return intrwDao.getBySearchCriteria(criteria);
	}

	public void save(Interviewer t) {
		
		if(validate(t)) {
			intrwDao.save(t);
		}	
	}
	public void update(Interviewer t, String... params) {
		
		if(validate(t)) {
		intrwDao.update(t, params);
		}
	}

	public void delete(Interviewer t) {
		intrwDao.delete(t);	
	}
	private static boolean validate(Interviewer interviewer) {

		// trim the leading and trailing white spaces if present
		// replace extra white spaces with single white space
		interviewer.setInterviewerName(interviewer.getInterviewerName().trim().replaceAll("\\s+", " ").toUpperCase());
		interviewer.setInterviewerEmail(interviewer.getInterviewerEmail().trim().toLowerCase());
		interviewer.setInterviewerSkill(interviewer.getInterviewerSkill().toUpperCase().trim().replaceAll("\\s+", " "));

		if (validate.validateName(interviewer.getInterviewerName())) {
			if (validate.validateEmail(interviewer.getInterviewerEmail())) {
					return true;
				}
			}
		return false;
	}
}
