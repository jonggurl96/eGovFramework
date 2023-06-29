package com.zzong.egovframework.web.persistence.jpa;

import com.zzong.egovframework.persistence.dto.PostDTO;
import com.zzong.egovframework.persistence.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("postJPAController")
@RequestMapping("/jpa/post")
public class PostController {
    
    @Resource(name = "postJPA")
    private PostService postService;
    
    @GetMapping("/start/{kwrd}")
    public List<PostDTO> findStartsWith(@PathVariable String kwrd) {
        Logger logger = LogManager.getLogger();
        logger.info("Using JPA, find all post starting with {}", kwrd);
    
        List<PostDTO> retVal = postService.getAll(kwrd);
        logger.debug("Returned value from http://localhost:8080/jpa/post/start/fb7: {}", retVal);
        return retVal;
    }
}
