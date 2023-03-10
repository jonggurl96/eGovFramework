
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/egovframework/spring/context-datasource.xml",
		"file:src/main/resources/egovframework/spring/context-mapper.xml"})
public class SqlSessionFactoryTest {

	@Inject
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void sqlSessionFactoryTest() throws Exception {
		System.out.println(sqlSessionFactory);
	}
	
	@Test
	public void testSession() throws Exception {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			System.out.println(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
