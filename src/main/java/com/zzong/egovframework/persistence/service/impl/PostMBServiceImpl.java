package com.zzong.egovframework.persistence.service.impl;

import com.zzong.egovframework.persistence.dto.PostDTO;
import com.zzong.egovframework.persistence.repository.PostMapper;
import com.zzong.egovframework.persistence.service.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("postMyBatis")
public class PostMBServiceImpl implements PostService {
    
    @Resource(name = "postMapper")
    private PostMapper postInterface;
    
    @Override
    public List<PostDTO> getAll(String title) {
        return postInterface.selectAll(title).stream().map(PostDTO::vo2dto).toList();
    }
}
