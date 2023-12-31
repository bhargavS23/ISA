package com.zettamine.isa.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.zettamine.isa.dao.IsaDao;
import com.zettamine.isa.dao.impl.IsaSkillDaoImpl;
import com.zettamine.isa.dto.SearchCriteria;
import com.zettamine.isa.dto.Skill;
import com.zettamine.isa.service.IsaService;

public class SkillService implements IsaService<Skill, SearchCriteria> {

	static IsaDao< Skill, SearchCriteria> dao = new IsaSkillDaoImpl();
	@Override
	public Optional<Skill> get(int id) {
		Optional<Skill>skillbyId = null;
		try {
			skillbyId   = dao.get(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return skillbyId;
	}

	@Override
	public List<Skill> getAll() {
		
		return dao.getAll();
	}

	@Override
	public List<Skill> getBySearchCriteria(SearchCriteria criteria) {
		return null;
	}

	@Override
	public void save(Skill t) {
		dao.save(t);
	}

	@Override
	public void update(Skill t, String... params) {
		dao.update(t, params);
		
	}

	@Override
	public void delete(Skill t) {
		dao.delete(t);
	}
}
