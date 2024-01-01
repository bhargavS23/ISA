package com.zettamine.isa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zettamine.isa.dao.IsaDao;
import com.zettamine.isa.dbconfig.ConnectionFactory;
import com.zettamine.isa.dto.Applicant;
import com.zettamine.isa.dto.Interviewer;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.Skill;

public class ApplicantDaoImpl implements IsaDao<Applicant, IsaSearchCriteria> {

	private Connection con = null;
	private PreparedStatement presat = null;

	public ApplicantDaoImpl() {
		con = ConnectionFactory.getDBConnection();
	}
	
	@Override
	public Optional<Applicant> get(int id) {
		
		String querey = "SELECT * FROM isa.applicant WHERE applicant_id =?";
		Optional<Applicant> appOpt= Optional.empty();
		try {
			presat = con.prepareStatement(querey);
			presat.setInt(1, id);
			
			ResultSet rs = presat.executeQuery();
			IsaSkillDaoImpl skillDao = new IsaSkillDaoImpl();
			
			while(rs.next()) {
				
				int appId = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phNo = rs.getString(4);
				String hiQlf = rs.getString(5);
				Double agr = rs.getDouble(6);
				String remarks = rs.getString(7); 
				int skillId = rs.getInt(8);
				String stream  = rs.getString(9);
				Optional<Skill> optional = skillDao.get(skillId);
				Skill skill = optional.get();
				appOpt= Optional.ofNullable(new Applicant(appId, name, email, skill, phNo, hiQlf, stream, remarks, agr));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appOpt;
	}

	@Override
	public List<Applicant> getAll() {
		String querey = "SELECT * FROM isa.applicant";
		List<Applicant> appList = new ArrayList<Applicant>();
			
		try {
			presat = con.prepareStatement(querey);
			ResultSet rs = presat.executeQuery();
			IsaSkillDaoImpl skillDao = new IsaSkillDaoImpl();
			
			while(rs.next()) {
				
				int appId = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phNo = rs.getString(4);
				String hiQlf = rs.getString(5);
				Double agr = rs.getDouble(6);
				String remarks = rs.getString(7); 
				int skillId = rs.getInt(8);
				String stream  = rs.getString(9);
				Optional<Skill> optional = skillDao.get(skillId);
				Skill skill = optional.get();
				appList.add(new Applicant(appId, name, email, skill, phNo, hiQlf, stream, remarks, agr));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appList;
	}

	@Override
	public List<Applicant> getBySearchCriteria(IsaSearchCriteria criteria) {
		List<Applicant> intList = new ArrayList<>();
		IsaSkillDaoImpl skillDao = new IsaSkillDaoImpl();
		try {
			presat = con.prepareStatement("SELECT * FROM isa.applicant WHERE primary_skill = ?");
			presat.setInt(1, criteria.getSkillId());
			ResultSet rs = presat.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phNo = rs.getString(4);
				String hiQlf = rs.getString(5);
				Double agr = rs.getDouble(6);
				String remarks = rs.getString(7); 
				int skillId = rs.getInt(8);
				String stream  = rs.getString(9);
				Optional<Skill> optional = skillDao.get(skillId);
				Skill skill = optional.get();
				intList.add(new Applicant(id, name, email, skill, phNo, hiQlf, stream, remarks, agr));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return intList;
	}

	@Override
	public void save(Applicant t) {
		
		String quere = "INSERT INTO isa.applicant(applicant_name, email, phone_number, highest_qualification, "
						+ "total_aggregate, remarks, primary_skill, stream) "
				        + "VALUES(?,?,?,?,?,?,?,?)";
		try {
			presat = con.prepareStatement(quere);
			
			presat.setString(1, t.getApplicantName());
			presat.setString(2,t.getApplicantEmail() );
			presat.setString(3,t.getApplicantPhNo() );
			presat.setString(4,t.getApplicantQualification() );
			presat.setDouble(5, t.getApplicantAggrePercentage());
			presat.setString(6, t.getApplicantRemarks());
			presat.setInt(7, t.getApplicantSkill().getSkillId());
			presat.setString(8, t.getStream());
			
			presat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Applicant t, String... params) {
		String quere = "UPDATE isa.applicant SET applicant_name =?, email =?, phone_number=?, "
				+ "highest_qualification=?, total_aggregate =?, remarks =?, primary_skill =?, stream=?";
		try {
			presat = con.prepareStatement(quere);
			
			presat.setString(1, t.getApplicantName());
			presat.setString(2,t.getApplicantEmail() );
			presat.setString(3,t.getApplicantPhNo() );
			presat.setString(4,t.getApplicantQualification() );
			presat.setDouble(5, t.getApplicantAggrePercentage());
			presat.setString(6, t.getApplicantRemarks());
			presat.setInt(7, t.getApplicantSkill().getSkillId());
			presat.setString(8, t.getStream());
			
			presat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Applicant t) {
		
		try {
			presat = con.prepareStatement("DELETE FROM isa.applicant WHERE applicant_id = ?");
			presat.setInt(1, t.getApplicantId());
			presat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
