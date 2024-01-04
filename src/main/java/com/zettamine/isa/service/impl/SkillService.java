package com.zettamine.isa.service.impl;

import java.util.List;
import java.util.Optional;

import com.zettamine.isa.dao.IsaDao;
import com.zettamine.isa.dao.impl.IsaSkillDaoImpl;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.SearchCriteria;
import com.zettamine.isa.dto.Skill;

public class SkillService {

	static IsaDao<Skill, IsaSearchCriteria> dao = new IsaSkillDaoImpl();
	
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

	public Integer save(Skill t) {
		
		t.setSkillDsec(t.getSkillDsec().toUpperCase().trim().replaceAll("\\s+", " "));	
		return dao.save(t);
	}

	public void update(Skill t, String... params) {
		t.setSkillDsec(t .getSkillDsec().toUpperCase().trim().replaceAll("\\s+", " "));
		dao.update(t, params);
	}
	public void delete(Skill t) {
		dao.delete(t);
	}
	
	public List<Skill> getBySearchCriteria(IsaSearchCriteria criteria){
		return dao.getBySearchCriteria(criteria);
	}
	
	public Integer getSkillById(String desc) {
		IsaSearchCriteria src = new IsaSearchCriteria();
		 src.setSkill_desc(desc);
		 List<Skill> list = dao.getBySearchCriteria(src);
		 return list.get(0).getSkillId();
	}
}
