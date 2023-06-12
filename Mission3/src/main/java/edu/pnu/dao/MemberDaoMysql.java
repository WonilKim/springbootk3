package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.pnu.domain.MemberVO;


public class MemberDaoMysql implements MemberInterface {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/musthave";
	private String id = "musthave";
	private String pwd = "tiger";

	protected Connection con;
	protected Statement stmt;
	protected PreparedStatement psmt;
	protected ResultSet rs;

	public MemberDaoMysql() {
		this.con = getConnection();

	}


	@Override
	public ArrayList<MemberVO> getMembers() {
		try {
			this.stmt = con.createStatement();
			String query = "select * from member2";
			ResultSet rs = stmt.executeQuery(query);

			ArrayList<MemberVO> list = new ArrayList<MemberVO>();

			while (rs.next()) {

				MemberVO m = new MemberVO().builder()
						.id(rs.getInt("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate")).build();

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

	@Override
	public MemberVO getMember(int id) {
		try {
			this.stmt = con.createStatement();
			String query = String.format("select * from member2 where id=%d", id);
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next() == true) {

				MemberVO m = new MemberVO().builder()
						.id(rs.getInt("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate")).build();
	
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

	@Override
	public MemberVO insertMember(MemberVO member) {
		try {
			String query = "INSERT INTO member2 (pass, name) VALUES (?,?)";
			psmt = con.prepareStatement(query);
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

	@Override
	public MemberVO updateMember(int id, MemberVO member) {
	       try {
	            // 쿼리문 템플릿 준비
	            String query = "UPDATE member2"
	                         + " SET pass=?, name=? "
	                         + " WHERE id=?";

	            // 쿼리문 준비
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, member.getPass());
	            psmt.setString(2, member.getName());
	            psmt.setInt(3, id);

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

	@Override
	public MemberVO deleteMember(int id) {
		MemberVO member = getMember(id);
		
        try {
        	        	
            String query = "DELETE FROM member2 WHERE id=?";
            psmt = con.prepareStatement(query);
            psmt.setInt(1, id);
            int result = psmt.executeUpdate();
            System.out.println(result + " data deleted");
            
            if(result != 0)
            	return member;
            else
            	return null;
        }
        catch (Exception e) {
            System.out.println("게시물 삭제 중 예외 발생");
            e.printStackTrace();
            
            return null;
        }

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
