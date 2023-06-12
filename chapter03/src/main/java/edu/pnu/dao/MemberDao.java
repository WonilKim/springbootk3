package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.Member;

public class MemberDao {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/musthave";
	private String id = "musthave";
	private String pwd = "tiger";
	
	private Connection con;
	private Statement stmt;
	private PreparedStatement psmt;
	private ResultSet rs;

	public MemberDao() {
		this.con = getConnection();
	}
	
	public Connection getConnection() {
		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("데이터베이스가 연결되었습니다.");
			
			return con;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	// 연결 해제(자원 반납)
	public void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
				con = null;
			}

			System.out.println("JDBC 자원 해제");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	public Member insertMember(Member m) {
		try {

			String query = "insert into member3 (name, age, nickname) values (?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1,  m.getName());
			psmt.setInt(2, m.getAge());
			psmt.setString(3,  m.getNickname());
			
			int result = psmt.executeUpdate();
			
		System.out.println(result + " data inserted");
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		return m;

	}
	
	public ArrayList<Member> getMembers() {
		try {
			this.stmt = con.createStatement();
			String query = "select * from member3";
			ResultSet rs = stmt.executeQuery(query);

			ArrayList<Member> list = new ArrayList<Member>();

			while (rs.next()) {

				Member m = new Member().builder()
						.id(rs.getLong("id"))
						.name(rs.getString("name"))
						.age(rs.getInt("age"))
						.nickname(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build();

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
	
	public Member getMember(Long id) {
		try {
			this.stmt = con.createStatement();
			String query = String.format("select * from member3 where id=%d", id);
			ResultSet rs = stmt.executeQuery(query);

			rs.next();

			Member m = new Member().builder()
					.id(rs.getLong("id"))
					.name(rs.getString("name"))
					.age(rs.getInt("age"))
					.nickname(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();
			
			rs.close();

			return m;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
	public Member updateMember(Member member) {

        try {
            // 쿼리문 템플릿 준비
            String query = "UPDATE member3"
                         + " SET name=?, age=?, nickname=? "
                         + " WHERE id=?";

            // 쿼리문 준비
            psmt = con.prepareStatement(query);
            psmt.setString(1, member.getName());
            psmt.setInt(2, member.getAge());
            psmt.setString(3, member.getNickname());
            psmt.setLong(4, member.getId());

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
		Member m = getMember(id);
		
        try {
        	        	
            String query = "DELETE FROM member3 WHERE id=?";
            psmt = con.prepareStatement(query);
            psmt.setLong(1, id);
            int result = psmt.executeUpdate();
            System.out.println(result + " data deleted");
        }
        catch (Exception e) {
            System.out.println("게시물 삭제 중 예외 발생");
            e.printStackTrace();
        }
        return m;

	}

}
