package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByTitleLike(String keyword);
	
	List<Board> findByTitleLikeAndCntGreaterThan(String keyword, long value);

	List<Board> findByCntBetweenOrderBySeqAsc(long value1, long value2);
	
	List<Board> findByTitleLikeOrContentLikeOrderBySeqDesc(String keyword1, String keyword2);

	List<Board> findByTitleLike(String keyword, Pageable paging);

	public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
