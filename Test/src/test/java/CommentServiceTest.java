import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cpservice.board.domain.CommentVO;
import cpservice.board.service.CommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/egovframework/spring/context-datasource.xml",
"file:src/main/resources/egovframework/spring/context-mapper.xml"})
public class CommentServiceTest {

	@Inject
	private CommentService service;
	
	private static final Logger logger = LoggerFactory.getLogger(CommentServiceTest.class);
	
	@Test
	public void updateTest() throws Exception {
		CommentVO vo = new CommentVO();
		vo.setCno(7);
		vo.setContent("3계층 댓글");
		logger.info(vo.toString());
		service.modifyComment(vo);
	}
}
