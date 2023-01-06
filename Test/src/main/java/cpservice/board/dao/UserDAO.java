package cpservice.board.dao;

import cpservice.board.domain.UserVO;
import cpservice.board.dto.LoginDTO;

public interface UserDAO {
	
	public UserVO login(LoginDTO dto) throws Exception;
	
	public boolean regist(LoginDTO dto) throws Exception;

}
