package com.zzong.egovframework.web.persistence.mybatis;

import com.zzong.egovframework.persistence.dto.PostDTO;
import com.zzong.egovframework.persistence.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("postMyBatisController")
@RequestMapping("/mybatis/post")
public class PostController {
    
    @Resource(name = "postMyBatis")
    private PostService postService;
    
    @GetMapping("/all")
    public List<PostDTO> getAllPosts() {
        return postService.getAll(null);
    }
    
    @GetMapping("/start/{kwrd}")
    public List<PostDTO> getAllPosts(@PathVariable String kwrd) {
        return postService.getAll(kwrd);
    }
    
}
