package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoList implements MemberInterface {

	private ArrayList<MemberVO> list;
	
	public MemberDaoList() {
		list = new ArrayList<>();
		for (int i = 0 ; i < 20 ; i++) {
			list.add(new MemberVO(i, "1234", "이름"+i, new Date()));
		}
	}
	

	@Override
	public ArrayList<MemberVO> getMembers(LogDao logDao) {
		return list;
	}

	@Override
	public MemberVO getMember(LogDao logDao, int id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}

	@Override
	public MemberVO insertMember(LogDao logDao, MemberVO member) {
		member.setId(list.size() + 1);
		member.setRegidate(new Date());
		list.add(member);
		return member;
	}

	@Override
	public MemberVO updateMember(LogDao logDao, int id, MemberVO member) {
		for (MemberVO m : list) {
			if (m.getId() == member.getId()) {
				m.setName(member.getName());
				m.setPass(member.getPass());
				return m;
			}
		}
		return null;
	}

	@Override
	public MemberVO deleteMember(LogDao logDao, int id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				return m;
			}
		}
		return null;
	}

}
