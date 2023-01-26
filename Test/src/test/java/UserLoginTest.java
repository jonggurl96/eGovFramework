import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cpservice.board.dto.LoginDTO;
import cpservice.board.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/egovframework/spring/context-mapper.xml",
		"file:src/main/resources/egovframework/spring/context-datasource.xml"})
public class UserLoginTest {

	@Resource(name = "userMapper")
	private UserMapper userMapper;
	
	private static Logger logger = LoggerFactory.getLogger(UserLoginTest.class);
	
	@Test
	public void loginTest() throws Exception {
		LoginDTO dto = new LoginDTO();
		dto.setId("user00");
		dto.setPw("3541");
		logger.info(userMapper.login(dto).toString());
	}
}
