<<<<<<<< HEAD:Test/Test/src/main/java/cpservice/board/service/UserService.java
package cpservice.board.service;
========
package cpservice.board.mapper;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
>>>>>>>> 2373352726d87fcef8a8d58a812d8d5bd3d801f2:Test/src/main/java/cpservice/board/mapper/UserMapper.java

import cpservice.board.domain.UserVO;
import cpservice.board.dto.LoginDTO;

<<<<<<<< HEAD:Test/Test/src/main/java/cpservice/board/service/UserService.java
public interface UserService {
========
@Mapper("userMapper")
public interface UserMapper {
>>>>>>>> 2373352726d87fcef8a8d58a812d8d5bd3d801f2:Test/src/main/java/cpservice/board/mapper/UserMapper.java

	public UserVO login(LoginDTO dto) throws Exception;
	
	public boolean regist(LoginDTO dto) throws Exception;
<<<<<<<< HEAD:Test/Test/src/main/java/cpservice/board/service/UserService.java
	
========
>>>>>>>> 2373352726d87fcef8a8d58a812d8d5bd3d801f2:Test/src/main/java/cpservice/board/mapper/UserMapper.java
}
