package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByTitleLike(String keyword);
	
	List<Board> findByTitleLikeAndCntGreaterThan(String keyword, long value);

	List<Board> findByCntBetweenOrderBySeqAsc(long value1, long value2);
	
	List<Board> findByTitleLikeOrContentLikeOrderBySeqDesc(String keyword1, String keyword2);

}
