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
import com.zettamine.isa.dto.Interviewer;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.Skill;

public class InterviewerDaoImpl implements IsaDao<Interviewer, IsaSearchCriteria> {

	private Connection con = null;
	private PreparedStatement presat = null;
	
	public  InterviewerDaoImpl() {
		
		con = ConnectionFactory.getDBConnection();
	}
	
	@Override
	public Optional<Interviewer> get(int id){
		IsaSkillDaoImpl skillDao = new IsaSkillDaoImpl();
		Optional<Interviewer> interviewerOpt = null;
				try {
			presat = con.prepareStatement("SELECT * FROM isa.interviewer WHERE interviewer_id = ?");
			presat.setInt(1, id);
			ResultSet rs = presat.executeQuery();
			while(rs.next()) {
				int intId = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				int skillId = rs.getInt(4);
				Optional<Skill> skillOpt= skillDao.get(skillId);
				Skill skill = skillOpt.get();
				interviewerOpt = Optional.ofNullable(new Interviewer(intId, name, email, skill));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return interviewerOpt;
	}

	@Override
	public List<Interviewer> getAll() {
		IsaSkillDaoImpl skillDao = new IsaSkillDaoImpl();
		List<Interviewer> intList = new ArrayList<>();
		try {
			presat =con.prepareStatement("SELECT * FROM isa.interviewer");
			ResultSet rs = presat.executeQuery();
			while(rs.next()) {
				int intId = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				int skillId = rs.getInt(4);
				Optional<Skill> skillOpt = skillDao.get(skillId);
				Skill skill = skillOpt.get();
				intList.add(new Interviewer(intId, name, email, skill));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return intList;
	}

	@Override
	public List<Interviewer> getBySearchCriteria(IsaSearchCriteria criteria) {

		List<Interviewer> intList = new ArrayList<>();
		IsaSkillDaoImpl skillDao = new IsaSkillDaoImpl();
		try {
			presat = con.prepareStatement("SELECT * FROM isa.interviewer WHERE primary_skill = ?");
			presat.setInt(1, criteria.getSkillId());
			ResultSet rs = presat.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				int skillId = rs.getInt(4);
				Optional<Skill> optional = skillDao.get(skillId);
				Skill skill = optional.get();
				intList.add(new Interviewer(id, name, email, skill));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return intList;
	}

	@Override
	public void save(Interviewer t) {
		try {
			presat = con.prepareStatement("INSERT INTO isa.interviewer(interviewer_name, email, primary_skill)"
					+ "VALUES(?,?,?)");
			presat.setString(1, t.getInterviewerName());
			presat.setString(1, t.getInterviewerEmail());
			presat.setInt(3, t.getInterviewerSkill().getSkillId());
			presat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Interviewer t, String... params) {
		try {
			presat = con.prepareStatement("UPDATE isa.interviewer set interviewer_name = ?,  email =? ,primary_skill = ?"
					+ " WHERE interviewer_id = ?");
			presat.setString(1, t.getInterviewerName());
			presat.setString(2, t.getInterviewerEmail());
			presat.setInt(3, t.getInterviewerSkill().getSkillId());
			presat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Interviewer t) {
		
		try {
			presat = con.prepareStatement("DELETE isa.interviewer WHERE interviewer_id = ?");
			presat.setInt(1,t.getInterviewerId());
			presat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
