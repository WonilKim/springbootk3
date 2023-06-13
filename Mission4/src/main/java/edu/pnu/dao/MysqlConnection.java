package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlConnection {
	
	protected static String driver = "com.mysql.cj.jdbc.Driver";
	protected static String url = "jdbc:mysql://localhost:3306/musthave";
	protected static String id = "musthave";
	protected static String pwd = "tiger";

	protected static Connection con = null;
	protected static Statement stmt = null;
	protected static PreparedStatement psmt = null;
	protected static ResultSet rs = null;
	
	public MysqlConnection() {
		if(con == null)
			this.con = getConnection();
	}


	public Connection getConnection() {
		try {
			// JDBC 드라이버 로드
			Class.forName(driver);
			// DB에 연결
			con = DriverManager.getConnection(url, id, pwd);

			System.out.println("데이터베이스가 연결되었습니다.");
			return con;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 연결 해제(자원 반납)
	public void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();

			System.out.println("JDBC 자원 해제");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
