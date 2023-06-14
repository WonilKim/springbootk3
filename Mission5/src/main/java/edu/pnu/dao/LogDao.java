package edu.pnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.DBLog;

@Repository
public class LogDao extends MysqlConnection {

//	protected static Statement stmt = null;
//	protected static PreparedStatement psmt = null;
//	protected static ResultSet rs = null;
//
//	@Autowired
//	private DataSource dataSource;

//	public LogDao() {
//		super();
//		
//		if(con == null)
//			this.con = getConnection();
//
//	}
	
	public ArrayList<DBLog> getLogs() {
		try(Connection con = dataSource.getConnection()) {
			this.stmt = con.createStatement();
			String query = "select * from dblog";
			ResultSet rs = stmt.executeQuery(query);

			ArrayList<DBLog> list = new ArrayList<DBLog>();

			while (rs.next()) {

				DBLog m = new DBLog().builder()
						.id(rs.getInt("id"))
						.method(rs.getString("method"))
						.sqlstring(rs.getString("sqlstring"))
						.regidate(rs.getDate("regidate"))
						.success(rs.getBoolean("success")).build();
	
				list.add(m);

			}
			rs.close();

			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public DBLog getLog(int id) {
		try(Connection con = dataSource.getConnection()) {
			this.stmt = con.createStatement();
			String query = String.format("select * from dblog where id=%d", id);
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next() == true) {

				DBLog m = new DBLog().builder()
						.id(rs.getInt("id"))
						.method(rs.getString("method"))
						.sqlstring(rs.getString("sqlstring"))
						.regidate(rs.getDate("regidate"))
						.success(rs.getBoolean("success")).build();
	
				rs.close();

				return m;
			} else {
				System.out.println("해당하는 데이터가 없습니다.");
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}


	
	public DBLog insertLog(DBLog dblog) {
		try(Connection con = dataSource.getConnection()) {
			String query = "INSERT INTO dblog (method, sqlstring, success) VALUES (?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dblog.getMethod());
			psmt.setString(2, dblog.getSqlstring());
			psmt.setBoolean(3, dblog.isSuccess());

			int result = psmt.executeUpdate();
			System.out.println(result + " log inserted");
			
		} catch (Exception e) {
			System.out.println("log 입력 중 예외 발생");
			e.printStackTrace();
		}
		return dblog;

	}


}
