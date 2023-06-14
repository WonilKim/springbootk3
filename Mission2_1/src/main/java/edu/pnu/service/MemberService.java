package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
//	public MemberService() {
//		memberDao = new MemberDao();
//	}
	
	public Member getMember(Long id) {
		return memberDao.getMember(id);
	}

	public List<Member> getMembers() {
		return memberDao.getMembers();
	}

	public Member insertMember(Member member) {
		return memberDao.insertMember(member);
	}

	public Member updateMember(Member member) {
		return memberDao.updateMember(member);
	}

	public Member deleteMember(Long id) {
		return memberDao.deleteMember(id);
	}

}
