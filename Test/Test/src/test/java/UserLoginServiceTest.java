import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cpservice.board.dto.LoginDTO;
import cpservice.board.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/egovframework/spring/context-datasource.xml",
		"file:src/main/resources/egovframework/spring/context-mapper.xml"})
public class UserLoginServiceTest {

	@Inject
	private UserService service;
	
	private static final Logger logger = LoggerFactory.getLogger(UserLoginServiceTest.class);
	
	@Test
	public void loginServiceTest() throws Exception {
		LoginDTO dto = new LoginDTO();
		dto.setId("user00");
		dto.setPw("3541");
		logger.info(service.login(dto).toString());
	}
}
