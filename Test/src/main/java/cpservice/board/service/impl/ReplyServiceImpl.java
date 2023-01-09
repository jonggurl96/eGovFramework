package cpservice.board.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import cpservice.board.dao.ReplyDAO;
import cpservice.board.domain.ReplyVO;
import cpservice.board.service.ReplyService;

@Repository
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO dao;

	@Override
	public void addReply(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.create(vo);
	}

	@Override
	public List<ReplyVO> listReply(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.list(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	public void removeReply(int rno) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(rno);
	}

}
