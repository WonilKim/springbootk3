package edu.pnu.service;

import java.util.ArrayList;

import edu.pnu.dao.MemberDaoMysql;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {
	
	private MemberInterface memberDao;
	
	public MemberService() {
		memberDao = new MemberDaoMysql();
//		memberDao = new MemberDaoList();
	}
	
	public MemberVO getMember(int id) {
		return memberDao.getMember(id);
	}

	public ArrayList<MemberVO> getMembers() {
		return memberDao.getMembers();
	}

	public MemberVO insertMember(MemberVO member) {
		return memberDao.insertMember(member);
	}

	public MemberVO updateMember(int id, MemberVO member) {
		return memberDao.updateMember(id, member);
	}

	public MemberVO deleteMember(int id) {
		return memberDao.deleteMember(id);
	}

}
