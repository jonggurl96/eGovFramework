package cpservice.board.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import cpservice.board.dao.UserDAO;
import cpservice.board.domain.UserVO;
import cpservice.board.dto.LoginDTO;
import cpservice.board.service.UserService;

@Repository
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO dao;

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.login(dto);
	}

}
