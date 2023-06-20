package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	@Query("Select b from Board b where b.title like %?1% order by b.seq desc")
	List<Board> queryAnnotationTest1(String searchKeyword);
	
	@Query("Select b from Board b where b.title like %:searchKeyword% order by b.seq desc")
	List<Board> queryAnnotationTest2(String searchKeyword);	// 이름이 같으면 @Param 키워드 생략 가능
//	List<Board> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);
	
	@Query("Select b.seq, b.title, b.writer, b.createDate from Board b where b.title like %?1% order by b.seq desc")
	List<Object[]> queryAnnotationTest3(String searchKeyword);
	
	@Query(value="Select seq, title, writer, create_date from Board where title like %?1% order by seq desc", 
			nativeQuery=true)
	List<Object[]> queryAnnotationTest4(String searchKeyword);
	
	@Query("select b from Board b order by b.seq asc")
	List<Board> queryAnnotationTest5(Pageable paging);
	

	
}
