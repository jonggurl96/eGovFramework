package cpservice.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cpservice.board.domain.UserVO;

public class ReadAuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(ReadAuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		Object vo = request.getSession().getAttribute("loginInfo");
		if(vo == null) {
			logger.info("로그인 없이 조회 시도");
			response.sendRedirect("/user/login");
			return false;
		}
		String id = ((UserVO)vo).getId();
		logger.info(id + " read content");
		return true;
	}
	
	
}
