package cpservice.board.dao;

import java.util.List;

import cpservice.board.domain.ReplyVO;

public interface ReplyDAO {

	public List<ReplyVO> list(int bno) throws Exception;
	
	public void create(ReplyVO vo) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public void delete(int rno) throws Exception;
}
