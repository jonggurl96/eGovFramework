
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cpservice.board.dao.BoardDAO;
import cpservice.board.domain.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/egovframework/spring/context-datasource.xml",
"file:src/main/resources/egovframework/spring/context-mapper.xml"})
public class BoardDAOTest {

	@Inject
	private BoardDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Test
	public void count() throws Exception {
		logger.info("COUNT: " + dao.getCountAllList());
	}
	
	@Test
	public void search() throws Exception {
		logger.info("search() .......");
		List<BoardVO> list = dao.searchWithPage("title", "itl", 1, 10);
		for(BoardVO vo : list) {
			logger.info(vo.toString());
		}
	}
	
	@Test
	public void update() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setTitle("updated title");
		vo.setContent("updated content");
		vo.setBno(128);
		dao.update(vo);
	}
	
	@Test
	public void read() throws Exception {
		System.out.println(dao.read(128));
	}
	
	@Test
	public void delete() throws Exception {
		dao.delete(2);
	}
	
	@Test
	public void insert() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setTitle("new title");
		vo.setContent("new content");
		vo.setWriter("new user");
		dao.insert(vo);
	}
	
	@Test
	public void listAll() throws Exception {
		List<BoardVO> list = dao.list();
		for(BoardVO vo : list) {
			System.out.println(vo);
		}
	}
	
	@Test
	public void paginatedList() throws Exception {
		List<BoardVO> list = dao.getListWithPage(20, 10);
		for(BoardVO vo : list) {
			logger.info("bno: " + vo.getBno());
		}
	}
}
