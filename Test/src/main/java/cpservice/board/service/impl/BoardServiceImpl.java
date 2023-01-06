package cpservice.board.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import cpservice.board.dao.BoardDAO;
import cpservice.board.domain.BoardVO;
import cpservice.board.service.BoardService;

@Repository
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;

	@Override
	public BoardVO select(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.read(bno);
	}

	@Override
	public void regist(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}

	@Override
	public void modify(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	public void remove(int bno) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public int getCount() throws Exception {
		// TODO Auto-generated method stub
		return dao.getCountAllList();
	}

	@Override
	public List<BoardVO> getList(int start, int rcpp) throws Exception {
		// TODO Auto-generated method stub
		return dao.getListWithPage(start, rcpp);
	}

}
