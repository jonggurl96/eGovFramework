
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cpservice.board.mapper.BoardMapper;
import cpservice.board.search.Search;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/egovframework/spring/context-datasource.xml",
"file:src/main/resources/egovframework/spring/context-mapper.xml"})
public class BoardDAOTest {

	@Resource(name = "boardMapper")
	private BoardMapper boardMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Test
	public void search() throws Exception {

		Search search = new Search("title", "제", 1, 10);
		logger.info("SEARCH: " + boardMapper.search(search));
	}
}
