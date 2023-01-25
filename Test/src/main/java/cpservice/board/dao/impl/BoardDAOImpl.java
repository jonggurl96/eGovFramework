package cpservice.board.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cpservice.board.dao.BoardDAO;
import cpservice.board.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession session;
	
	private static final String namespace = "cpservice.board.mapper.BoardMapper";

	@Override
	public BoardVO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".read", bno);
	}

	@Override
	public void insert(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace + ".delete", bno);
	}

	@Override
	public List<BoardVO> list() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".listAll");
	}

	@Override
	public int getCountAllList() throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".countAllList");
	}

	@Override
	public List<BoardVO> getListWithPage(int start, int rcpp) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("rccp", rcpp);
		return session.selectList(namespace + ".paginatedList", map);
	}

	@Override
	public List<BoardVO> searchWithPage(String tag, String keyword, int start, int rcpp) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tag", tag);
		map.put("keyword", keyword);
		map.put("start", start);
		map.put("rcpp", rcpp);
		return session.selectList(namespace + ".search", map);
	}

	@Override
	public int getCountSearched(String tag, String keyword) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tag", tag);
		map.put("keyword", keyword);
		return session.selectOne(namespace + ".countSearchList", map);
	}
	
}
