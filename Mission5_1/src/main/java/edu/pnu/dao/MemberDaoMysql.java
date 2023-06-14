package edu.pnu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.DBLog;
import edu.pnu.domain.MemberVO;

//@Repository	// MemberConfig 에서 @Bean으로 등록
public class MemberDaoMysql extends MysqlConnection implements MemberInterface {

	@Override
	public ArrayList<MemberVO> getMembers(LogDao logDao) {
		DBLog dbLog = new DBLog();
		dbLog.setMethod("select");

		try(Connection con = dataSource.getConnection()) {
			this.stmt = con.createStatement();
			String query = "select * from member2";

			System.out.println(query);
			dbLog.setSqlstring(query);

			ResultSet rs = stmt.executeQuery(query);

			dbLog.setSuccess(true);
			logDao.insertLog(dbLog);

			ArrayList<MemberVO> list = new ArrayList<MemberVO>();

			while (rs.next()) {

				MemberVO m = new MemberVO().builder().id(rs.getInt("id")).pass(rs.getString("pass"))
						.name(rs.getString("name")).regidate(rs.getDate("regidate")).build();

				list.add(m);

			}
			rs.close();

			return list;

		} catch (SQLException e) {
			System.out.println("데이터 조회 중 예외 발생");

			dbLog.setSuccess(false);
			logDao.insertLog(dbLog);

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public MemberVO getMember(LogDao logDao, int id) {
		DBLog dbLog = new DBLog();
		dbLog.setMethod("select");

		try(Connection con = dataSource.getConnection()) {
			this.stmt = con.createStatement();
			String query = String.format("select * from member2 where id=%d", id);

			System.out.println(query);
			dbLog.setSqlstring(query);

			ResultSet rs = stmt.executeQuery(query);

			dbLog.setSuccess(true);
			logDao.insertLog(dbLog);

			if (rs.next() == true) {

				MemberVO m = new MemberVO().builder().id(rs.getInt("id")).pass(rs.getString("pass"))
						.name(rs.getString("name")).regidate(rs.getDate("regidate")).build();

				rs.close();

				return m;
			} else {
				System.out.println("해당하는 데이터가 없습니다.");
				return null;
			}

		} catch (SQLException e) {
			System.out.println("데이터 조회 중 예외 발생");
			
			dbLog.setSuccess(false);
			logDao.insertLog(dbLog);

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public MemberVO insertMember(LogDao logDao, MemberVO member) {
		DBLog dbLog = new DBLog();
		dbLog.setMethod("insert");

		try(Connection con = dataSource.getConnection()) {
			String query = "INSERT INTO member2 (pass, name) VALUES (?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());

			String queryCompleteString = psmt.toString().split(":")[1].trim();
			System.out.println(queryCompleteString);
			dbLog.setSqlstring(queryCompleteString);

			int result = psmt.executeUpdate();
			System.out.println(result + " data inserted");

			dbLog.setSuccess(true);
			logDao.insertLog(dbLog);

		} catch (Exception e) {
			System.out.println("데이터 입력 중 예외 발생");

			dbLog.setSuccess(false);
			logDao.insertLog(dbLog);

			e.printStackTrace();
		}
		return member;

	}

	@Override
	public MemberVO updateMember(LogDao logDao, int id, MemberVO member) {
		DBLog dbLog = new DBLog();
		dbLog.setMethod("update");

		try(Connection con = dataSource.getConnection()) {
			// 쿼리문 템플릿 준비
			String query = "UPDATE member2" + " SET pass=?, name=? " + " WHERE id=?";

			// 쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			psmt.setInt(3, id);

			String queryCompleteString = psmt.toString().split(":")[1].trim();
			System.out.println(queryCompleteString);
			dbLog.setSqlstring(queryCompleteString);

			// 쿼리문 실행
			int result = psmt.executeUpdate();
			System.out.println(result + " data updated");

			dbLog.setSuccess(true);
			logDao.insertLog(dbLog);

		} catch (Exception e) {
			System.out.println("데이터 수정 중 예외 발생");

			dbLog.setSuccess(false);
			logDao.insertLog(dbLog);

			e.printStackTrace();
		}
		return member;
	}

	@Override
	public MemberVO deleteMember(LogDao logDao, int id) {
		DBLog dbLog = new DBLog();
		dbLog.setMethod("delete");

		MemberVO member = getMember(logDao, id);

		try(Connection con = dataSource.getConnection()) {

			String query = "DELETE FROM member2 WHERE id=?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);

			String queryCompleteString = psmt.toString().split(":")[1].trim();
			System.out.println(queryCompleteString);
			dbLog.setSqlstring(queryCompleteString);

			int result = psmt.executeUpdate();
			System.out.println(result + " data deleted");

			dbLog.setSuccess(true);
			logDao.insertLog(dbLog);

			if (result != 0)
				return member;
			else
				return null;
		} catch (Exception e) {
			System.out.println("데이터 삭제 중 예외 발생");

			dbLog.setSuccess(false);
			logDao.insertLog(dbLog);

			e.printStackTrace();

			return null;
		}

	}

}
