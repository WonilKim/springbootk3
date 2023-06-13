package edu.pnu.dao;

import java.util.ArrayList;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {
	
	ArrayList<MemberVO> getMembers(LogDao logDao);
	
	MemberVO getMember(LogDao logDao, int id);
	
	MemberVO insertMember(LogDao logDao, MemberVO member);
	
	MemberVO updateMember(LogDao logDao, int id, MemberVO member);
	
	MemberVO deleteMember(LogDao logDao, int id);

}
