package cpservice.board.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cpservice.board.domain.BoardVO;
import cpservice.board.service.BoardService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@RestController
public class BoardSearchController {
	
	@Inject
	private BoardService service;
	
	@Value("#{pageConfigBean['page_size']}")
	private String pageSize;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardSearchController.class);

	@RequestMapping(value="/search", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> search(@RequestBody Map<String, Object> data, Model model) {
		
		ResponseEntity<Map<String, Object>> entity = null;
		List<BoardVO> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		String tag = (String) data.get("tag");
		String keyword = (String) data.get("keyword");
		int page = Integer.parseInt(data.get("page").toString());
		int rcpp = Integer.parseInt(data.get("rcpp").toString());
		
		logger.info("search(): tag = " + tag + ", keyword = " + keyword);
		logger.info("search(): page = " + page + ", rcpp = " + rcpp);
		
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(page);
		pageInfo.setRecordCountPerPage(rcpp);
		pageInfo.setPageSize(Integer.parseInt(pageSize));
		
		logger.info("page: " + pageInfo.getCurrentPageNo() + ", rcpp: " + pageInfo.getRecordCountPerPage());
		
		try {
			if(tag.equals("all")) {
				//검색 query가 존재하지 않을 경우
				list = service.getList(pageInfo.getFirstRecordIndex(), rcpp);
				pageInfo.setTotalRecordCount(service.getCount());
			} else {
				//검색 query가 존재할 경우
				list = service.getList(tag, keyword, pageInfo.getFirstRecordIndex(), rcpp);
				pageInfo.setTotalRecordCount(service.getCountSearched(tag, keyword));
			}
			map.put("list", list);
			map.put("pageInfo", pageInfo);
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
