package cpservice.board.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cpservice.board.domain.UserVO;
import cpservice.board.dto.LoginDTO;
import cpservice.board.service.UserService;

@Controller
@RequestMapping(value="/user/*")
public class UserController {
	
	@Inject
	private UserService service;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public void login() throws Exception {
		logger.info("move to login page");
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String loginpost(LoginDTO dto, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
		UserVO vo = service.login(dto);
		logger.info("login " + vo);
		if(vo == null) {
			rttr.addFlashAttribute("msg", "로그인 실패");
			return "redirect:/user/login";
		}
		else {
			return "redirect:/board/paginatedList?page=1&rcpp=10";
		}
	}
	
	@RequestMapping(value="regist")
	public void regist() throws Exception {
		logger.info("user regist page .......");
	}
	
	@RequestMapping(value="regist", method=RequestMethod.POST)
	public String registPOST(LoginDTO dto, RedirectAttributes rttr) throws Exception {
		logger.info("user regist .......\nID: " + dto.getId() + ", PW: " + dto.getPw());
		try {
			if(service.regist(dto))
				return "redirect:/user/login";
		} catch (Exception e) {
			rttr.addFlashAttribute("msg", "회원가입 실패");
			return "redirect:/user/regist";
		}
		
		return "redirect:/user/regist";
		
	}

}
