package edu.pnu.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDaoMysql;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

//@Service	// MemberConfig 에서 @Bean으로 등록
public class MemberService {
	
	@Autowired
	private MemberInterface memberDao;
	
	@Autowired
	private LogDao logDao;
	
//	@Autowired
//	public MemberService(MemberInterface memberDao, LogDao logDao) {
//		this.memberDao = memberDao;
//		this.logDao = logDao;
//	}
	
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
