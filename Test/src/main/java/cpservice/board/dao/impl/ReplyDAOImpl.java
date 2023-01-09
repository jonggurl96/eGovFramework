package cpservice.board.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cpservice.board.dao.ReplyDAO;
import cpservice.board.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession session;
	
	private static final String namespace = "replyMapper";

	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".list", bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int rno) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace + ".delete", rno);
	}

}
