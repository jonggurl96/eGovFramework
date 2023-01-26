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
public class UserRegistTest {
	
	@Inject
	private UserService service;
	
	private static final Logger logger = LoggerFactory.getLogger(UserRegistTest.class);
	
	@Test
	public void registTets() throws Exception {
		LoginDTO dto = new LoginDTO();
		dto.setId("jonggurl96");
		dto.setPw("123456789");
		
		if(service.regist(dto)) {
			logger.info("success");
		} else {
			logger.info("fail");
		}
	}

}
