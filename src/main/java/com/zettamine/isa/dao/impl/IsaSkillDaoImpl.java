package com.zettamine.isa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zettamine.isa.dao.IsaDao;
import com.zettamine.isa.dbconfig.ConnectionFactory;
import com.zettamine.isa.dto.Skill;
import com.zettamine.isa.dto.SearchCriteria;

public class IsaSkillDaoImpl implements IsaDao<Skill, SearchCriteria> {
	
	
	private Connection con = null;
	private PreparedStatement presat = null;

	public IsaSkillDaoImpl() {
		
		con =ConnectionFactory.getDBConnection();
	}
	@Override
	public Optional<Skill> get(int id)  {
		Optional<Skill> skill = null;
		try {
			presat=con.prepareStatement("SELECT * FROM isa.skill WHERE skill_id = ?");
			presat.setInt(1, id);
			ResultSet rs = presat.executeQuery();
			while(rs.next()) {
				int skillId = rs.getInt(1);
				String skillDesc = rs.getString(2);
				skill = Optional.ofNullable(new Skill(skillId, skillDesc));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return skill;
	}

	@Override
	public List<Skill> getAll() {
		
		List<Skill> skillList = new ArrayList<Skill>();
		try {
			presat = con.prepareStatement("SELECT * FROM isa.skill");
			ResultSet rs = presat.executeQuery();
			while(rs.next()) {
				
				Integer skillId = rs.getInt(1);
				String skillDesc= rs.getString(2);
				skillList.add(new Skill(skillId,skillDesc));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return skillList;
	}

	@Override
	public List<Skill> getBySearchCriteria(SearchCriteria criteria) {
		
		
		return null;
	}

	@Override
	public void save(Skill skill) {
		String query ="insert into isa.skill(skill_desc) values(?)";
		try {
			presat = con.prepareStatement(query);
			presat.setString(1, skill.getSkillDsec());
			presat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Skill t, String... params) {
	}

	@Override
	public void delete(Skill skill) {
		
		String query ="DELETE FROM isa.skill WHERE skill_id= ?";
		try {
			presat=con.prepareStatement(query);
			presat.setInt(1, skill.getSkillId());
			presat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
