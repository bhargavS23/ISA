package com.zettamine.isa.service.impl;

import java.util.List;
import java.util.Optional;

import com.zettamine.isa.dao.IsaDao;
import com.zettamine.isa.dao.impl.IsaSkillDaoImpl;
import com.zettamine.isa.dto.SearchCriteria;
import com.zettamine.isa.dto.Skill;

public class SkillService {

	static IsaDao<Skill, SearchCriteria> dao = new IsaSkillDaoImpl();
	
	public Skill get(int id) {
		
		Optional<Skill> skillOptional = dao.get(id);
		if(skillOptional.isPresent()) {
			return skillOptional.get();
		}
		return skillOptional.orElse(new Skill());
	}

	public List<Skill> getAll() {
		
		return dao.getAll();
	}

	public void save(Skill t) {
		
		t.setSkillDsec(t.getSkillDsec().toUpperCase().trim().replaceAll("\\s+", " "));	
		dao.save(t);
	}

	public void update(Skill t, String... params) {
		t.setSkillDsec(t .getSkillDsec().toUpperCase().trim().replaceAll("\\s+", " "));
		dao.update(t, params);
	}
	public void delete(Skill t) {
		dao.delete(t);
	}
}
