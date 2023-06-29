package edu.pnu.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.pnu.domain.Member;

// Member 테이블의 CRUD를 위한 인터페이스
public interface MemberRepository extends CrudRepository<Member, String> {
}
