package edu.pnu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class MysqlConnection {
		
	protected static Statement stmt = null;
	protected static PreparedStatement psmt = null;
	protected static ResultSet rs = null;

	@Autowired
	protected DataSource dataSource;


}
