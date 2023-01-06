import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.ibatis.session.SqlSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/egovframework/spring/context-datasource.xml",
		"file:src/main/resources/egovframework/spring/context-mapper.xml"})
public class SqlSessionTest {

	@Inject
	private SqlSession session;
	
	@Test
	public void sqlSessionTest() throws Exception {
		System.out.println(session);
	}
}
