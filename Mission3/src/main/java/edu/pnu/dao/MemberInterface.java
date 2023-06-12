package edu.pnu.dao;

import java.util.ArrayList;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {
	
	ArrayList<MemberVO> getMembers();
	
	MemberVO getMember(int id);
	
	MemberVO insertMember(MemberVO member);
	
	MemberVO updateMember(int id, MemberVO member);
	
	MemberVO deleteMember(int id);

}
