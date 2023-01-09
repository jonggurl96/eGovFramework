import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cpservice.board.domain.ReplyVO;
import cpservice.board.service.ReplyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/egovframework/spring/context-datasource.xml",
		"file:src/main/resources/egovframework/spring/context-mapper.xml"})
public class ReplyTest {
	
	@Inject
	private ReplyService service;
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyTest.class);
	
	@Test
	public void listTest() throws Exception {
		for(ReplyVO vo : service.listReply(10102)) {
			logger.info(vo.toString());
		}
	}
	
	@Test
	public void createTest() throws Exception {
		ReplyVO vo = new ReplyVO();
		vo.setBno(10101);
		vo.setReplyer("user00");
		vo.setReplytext("새롭게 추가된 댓글");
		service.addReply(vo);
	}
	
	@Test
	public void updateTest() throws Exception {
		ReplyVO vo = new ReplyVO();
		vo.setRno(7);
		vo.setReplyer("user00");
		vo.setReplytext("새롭게 수정된 댓글");
		service.modifyReply(vo);
	}
	
	@Test
	public void removeTest() throws Exception {
		service.removeReply(2);
		for(ReplyVO vo : service.listReply(10101)) {
			logger.info(vo.toString());
		}
	}

}
