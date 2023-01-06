package cpservice.board.dao;

import java.util.List;

import cpservice.board.domain.BoardVO;

public interface BoardDAO {

	public BoardVO read(int bno) throws Exception;
	
	public void insert(BoardVO vo) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(int bno) throws Exception;
	
	public List<BoardVO> list() throws Exception;
	
	public int getCountAllList() throws Exception;
	
	/**
	 * 한 페이지 내의 리스트들을 읽어오는 메소드
	 * @param start: 시작 인덱스
	 * @param rcpp: Record Count Per Page, 한 페이지 당 읽어오는 레코드 수
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> getListWithPage(int start, int rcpp) throws Exception;
	
}
