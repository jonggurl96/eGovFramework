package com.zzong.egovframework.web.persistence.jpa;

import com.zzong.egovframework.persistence.dto.PostDTO;
import com.zzong.egovframework.persistence.service.PostService;
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
        return postService.getAll(kwrd);
    }
}
