package com.zettamine.isa.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.zettamine.isa.dao.IsaDao;
import com.zettamine.isa.dao.impl.RecruiterDaoImpl;
import com.zettamine.isa.dto.Recruiter;
import com.zettamine.isa.dto.SearchCriteria;
import com.zettamine.isa.service.IsaService;

public class RecruiterServices implements IsaService<Recruiter, SearchCriteria> {

	static IsaDao recDao = new RecruiterDaoImpl();
	@Override
	public Optional<Recruiter> get(int id)  {
		
		return null;
	}

	@Override
	public List<Recruiter> getAll() {
		
		return recDao.getAll();
	}

	@Override
	public List<Recruiter> getBySearchCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return recDao.getBySearchCriteria(criteria);
	}

	@Override
	public void save(Recruiter t) {
		// TODO Auto-generated method stub
		recDao.save(t);
	}

	@Override
	public void update(Recruiter t, String... params) {
		// TODO Auto-generated method stub
		recDao.update(t, params);
	}

	@Override
	public void delete(Recruiter t) {
		// TODO Auto-generated method stub
		recDao.delete(t);
	}

}
