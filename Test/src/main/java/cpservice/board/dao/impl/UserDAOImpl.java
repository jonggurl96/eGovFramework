package cpservice.board.dao.impl;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cpservice.board.dao.UserDAO;
import cpservice.board.domain.UserVO;
import cpservice.board.dto.LoginDTO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Inject
	private SqlSession session;
	
	private static final String namespace = "cpservice.board.mapper.UserMapper";

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".login", dto);
	}

	@Override
	public boolean regist(LoginDTO dto) throws Exception {
		// TODO Auto-generated method stub
		int res = session.insert(namespace + ".regist", dto);
		if(res == 1) return true;
		return false;
	}

}
