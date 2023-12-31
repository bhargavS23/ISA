package com.zettamine.isa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zettamine.isa.dbconfig.ConnectionFactory;

public class smaple {

	public static void main(String[] args) {
	Connection con = ConnectionFactory.getDBConnection();
	String query ="insert into skill(skill_desc) values(?)";
	try {
		PreparedStatement pst = con.prepareCall(query);
		pst.setString(1, "Java");
		pst.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}

	}

}
