
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cpservice.board.domain.BoardVO;
import cpservice.board.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/egovframework/spring/context-datasource.xml",
"file:src/main/resources/egovframework/spring/context-mapper.xml"})
public class BoardServiceTest {

	@Inject
	private BoardService service;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);
	
	@Test
	public void count() throws Exception {
		logger.info("Count: " + service.getCount());
	}
	
	@Test
	public void regist() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setTitle("new title");
		vo.setContent("new content");
		vo.setWriter("jong");
		service.regist(vo);
	}
	
	@Test
	public void modify() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setTitle("수정된 제목");
		vo.setContent("수정된 내용");
		vo.setBno(8181);
		service.modify(vo);
	}
	
	@Test
	public void select() throws Exception {
		System.out.println(service.select(8182));
	}

	@Test
	public void remove() throws Exception {
		service.remove(125);
	}
	
	@Test
	public void listAll() throws Exception {
		List<BoardVO> list = service.listAll();
		for(BoardVO vo : list) {
			System.out.println(vo);
		}
	}
	
	@Test
	public void testListPaginated() throws Exception {
		List<BoardVO> list = service.getList(20, 10);
		for(BoardVO vo : list) {
			logger.info("bno: " + vo.getBno());
		}
	}
	
	@Test
	public void searchTest() throws Exception {
		List<BoardVO> list = service.getList("writer", "user00", 20, 10);
		for(BoardVO vo : list) {
			logger.info("bno: " + vo.getBno());
		}
	}
}
