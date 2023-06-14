package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.Member;

@Repository
public class MemberDao {

//	private String driver = "com.mysql.cj.jdbc.Driver";
//	private String url = "jdbc:mysql://localhost:3306/musthave";
//	private String id = "musthave";
//	private String pwd = "tiger";
//
//	protected Connection con;
	protected Statement stmt;
	protected PreparedStatement psmt;
	protected ResultSet rs;
	
	@Autowired
	private DataSource dataSource;

	public MemberDao() {
//		this.con = getConnection();

	}

	public Member getMember(Long id) {

		try {
			this.stmt = dataSource.getConnection().createStatement();
			String query = String.format("select * from member2 where id=%d", id);
			ResultSet rs = stmt.executeQuery(query);

			rs.next();

			Member m = new Member().builder().id(rs.getLong("id")).pass(rs.getString("pass")).name(rs.getString("name"))
					.regidate(rs.getDate("regidate")).build();

			rs.close();

			return m;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<Member> getMembers() {

		try {
			this.stmt = dataSource.getConnection().createStatement();
			String query = "select * from member2";
			ResultSet rs = stmt.executeQuery(query);

			List<Member> list = new ArrayList<Member>();

			while (rs.next()) {

				Member m = new Member().builder().id(rs.getLong("id")).pass(rs.getString("pass"))
						.name(rs.getString("name")).regidate(rs.getDate("regidate")).build();

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

	public Member insertMember(Member member) {

		try {
			String query = "INSERT INTO member2 (pass, name) VALUES (?,?)";
			psmt = dataSource.getConnection().prepareStatement(query);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());

			int result = psmt.executeUpdate();
			System.out.println(result + " data inserted");
			
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return member;

	}

	public Member updateMember(Member member) {

        try {
            // 쿼리문 템플릿 준비
            String query = "UPDATE member2"
                         + " SET pass=?, name=? "
                         + " WHERE id=?";

            // 쿼리문 준비
            psmt = dataSource.getConnection().prepareStatement(query);
            psmt.setString(1, member.getPass());
            psmt.setString(2, member.getName());
            psmt.setLong(3, member.getId());

            // 쿼리문 실행
            int result = psmt.executeUpdate();
            System.out.println(result + " data updated");
        }
        catch (Exception e) {
            System.out.println("게시물 수정 중 예외 발생");
            e.printStackTrace();
        }
        return member;

	}

	public Member deleteMember(Long id) {
		Member member = getMember(id);
		
        try {
        	        	
            String query = "DELETE FROM member2 WHERE id=?";
            psmt = dataSource.getConnection().prepareStatement(query);
            psmt.setLong(1, id);
            int result = psmt.executeUpdate();
            System.out.println(result + " data deleted");
        }
        catch (Exception e) {
            System.out.println("게시물 삭제 중 예외 발생");
            e.printStackTrace();
        }
        return member;

	}

//	public Connection getConnection() {
//		try {
//			// JDBC 드라이버 로드
//			Class.forName(driver);
//			// DB에 연결
//			con = DriverManager.getConnection(url, id, pwd);
//
//			System.out.println("데이터베이스가 연결되었습니다.");
//			return con;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	// 연결 해제(자원 반납)
//	public void closeConnection(Connection con) {
//		try {
//			if (con != null)
//				con.close();
//
//			System.out.println("JDBC 자원 해제");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	// 연결 해제(자원 반납)
//	public void close() {
//		try {
//			if (con != null)
//				con.close();
//
//			System.out.println("JDBC 자원 해제");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public ResultSet executeSelectQuery(String query) {

		try {
			stmt = dataSource.getConnection().createStatement();
			return stmt.executeQuery(query);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void showResultSet(ResultSet rs) {

		try {
			StringBuffer sqlString;
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String[] columnNames = new String[columnCount];
			for (int i = 0; i < columnNames.length; i++) {
				columnNames[i] = rsmd.getColumnName(i + 1);
			}

			System.out.println("-".repeat(columnCount * 19));
			System.out.print("| ");
			for (String col : columnNames) {
				// System.out.print(col + "\t");
				System.out.print(String.format("%-17s", col));
				System.out.print("| ");
			}
			System.out.println();
			System.out.println("-".repeat(columnCount * 19));

			if (rs != null) {
				while (rs.next()) {
					sqlString = new StringBuffer();
					sqlString.append("| ");

					for (int i = 1; i <= columnCount; i++) {
						Object obj = rs.getObject(i);

						if (obj == null) {
							sqlString.append("null");
							sqlString.append("\t| ");
						} else {
							int sqlTypes = rsmd.getColumnType(i);
							String temp = "";

							switch (sqlTypes) {
							case Types.VARCHAR:
							case Types.CHAR:
								temp = String.format("\"%s\"", rs.getString(i));
								sqlString.append(String.format("%-17s", temp));
								sqlString.append("| ");
								break;
							case Types.NULL:
								temp = "null";
								sqlString.append(String.format("%-17s", temp));
								sqlString.append("| ");
								break;
							case Types.TIMESTAMP:
								temp = String.format("\"%s\"", rs.getTimestamp(i));
								sqlString.append(String.format("%-17s", temp));
								sqlString.append("| ");
								break;

							case Types.DOUBLE:
								temp = String.format("%s", rs.getDouble(i));
								sqlString.append(String.format("%-17s", temp));
								sqlString.append("| ");
								break;

							case Types.INTEGER:
							case Types.BIGINT:
							case Types.SMALLINT:
								temp = String.format("%s", rs.getInt(i));
								sqlString.append(String.format("%-17s", temp));
								sqlString.append("| ");
								break;
							case Types.DECIMAL:
								temp = String.format("%s", rs.getBigDecimal(i));
								sqlString.append(String.format("%-17s", temp));
								sqlString.append("| ");
								break;

							/*
							 * default: if (obj != null) sqlString.append(obj.toString());
							 * 
							 * sqlString.append(","); break;
							 */
							} // switch

						} // else

					} // for

					System.out.println(sqlString);

				}
				System.out.println("-".repeat(columnNames.length * 19));

			} // if (rs != null)

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
