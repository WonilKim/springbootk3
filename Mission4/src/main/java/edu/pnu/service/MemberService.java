package edu.pnu.service;

import java.util.ArrayList;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDaoMysql;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {
	
	private MemberInterface memberDao;
	private LogDao logDao;
	
	public MemberService() {
		memberDao = new MemberDaoMysql();
//		memberDao = new MemberDaoList();
		
		logDao = new LogDao();
	}
	
	public MemberVO getMember(int id) {
		return memberDao.getMember(logDao, id);
	}

	public ArrayList<MemberVO> getMembers() {
		return memberDao.getMembers(logDao);
	}

	public MemberVO insertMember(MemberVO member) {
		return memberDao.insertMember(logDao, member);
	}

	public MemberVO updateMember(int id, MemberVO member) {
		return memberDao.updateMember(logDao, id, member);
	}

	public MemberVO deleteMember(int id) {
		return memberDao.deleteMember(logDao, id);
	}

}
