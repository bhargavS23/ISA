package com.zettamine.isa.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zettamine.isa.dao.IsaDao;
import com.zettamine.isa.dbconfig.ConnectionFactory;
import com.zettamine.isa.dto.IsaSearchCriteria;
import com.zettamine.isa.dto.ScheduleInterview;
import com.zettamine.isa.dto.Status;

public class ScheduleInterviewDaoImpl implements IsaDao<ScheduleInterview, IsaSearchCriteria> {
	
	private Connection con = null;
	private PreparedStatement presat = null;
	
	public ScheduleInterviewDaoImpl() {
		con = ConnectionFactory.getDBConnection();
	}

	@Override
	public Optional<ScheduleInterview> get(int id) {
		return Optional.empty();
	}

	@Override
	public List<ScheduleInterview> getAll() {
		
		List<ScheduleInterview> siList = new ArrayList<>();
		try {
			presat = con.prepareStatement("SELECT * FROM isa.interview_schedule");
			ResultSet rs = presat.executeQuery();
			while(rs.next()) {
				int scheduleId = rs.getInt(1);
				int applicantId = rs.getInt(2);
				int interviewerId = rs.getInt(3);
				int recruiterId = rs.getInt(4);
				Date date = rs.getDate(5);
				Time time = rs.getTime(6);
				Status status = Status.valueOf(rs.getString(7));
				String appName = rs.getString(8);
				String interName = rs.getString(9);
				String recName = rs.getString(10);
				siList.add(new ScheduleInterview(scheduleId, applicantId, interviewerId, recruiterId, appName, 
												interName, recName, date, time, status));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return siList;
	}

	@Override
	public List<ScheduleInterview> getBySearchCriteria(IsaSearchCriteria criteria) {
		
		return null;
	}

	@Override
	public void save(ScheduleInterview t) {
		String quere = "INSERT INTO isa.interview_schedule(applicant_id, interviewer_id, recuiter_id, interview_date, "
						+ "interview_time, status, applicant_name, interviewer_name, recruiter_name) "
						+ "VALUES(?,?,?,?,?,?,?,?,?)";	
		try {
			presat = con.prepareStatement(quere);
			
			presat.setInt(1, t.getApplicantId());
			presat.setInt(2, t.getInterviewerId());
			presat.setInt(3, t.getRecruiterId());
			presat.setDate(4, t.getScheduledDate());
			presat.setTime(5, t.getScheduledTime());
			presat.setString(6, t.getInterviewStatus().getValue());
			presat.setString(7,t.getApplicantName());
			presat.setString(8, t.getInterviewerName());
			presat.setString(9, t.getRecruiterName());
			
			presat.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ScheduleInterview t, String... params) {
		
		String quere ="UPDATE isa.interview_schedule set ";
		
	}

	@Override
	public void delete(ScheduleInterview t) {
		try {
			presat = con.prepareStatement("DELETE FROM isa.interview_schedule WHERE schedule_id = ?");
			presat.setInt(1, t.getInterviewerId());
			presat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
