package com.zzong.egovframework.web.dummy;

import com.zzong.egovframework.cmmn.idgnr.UUIdGenerator;
import com.zzong.egovframework.persistence.domain.Post;
import com.zzong.egovframework.persistence.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/init/post")
@RequiredArgsConstructor
public class PostInitController {
    
    private final PostInitService postInitService;
    
    private final UUIdGenerator uuIdGenerator;
    
    @GetMapping("/{amount}")
    @ResponseBody
    public List<PostDTO> initData(@PathVariable int amount) throws FdlException {
        List<Post> list = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
            String id = uuIdGenerator.getStringId();
            BigDecimal password = uuIdGenerator.getBigDecimalId();
            list.add(Post.builder()
                    .postId(id)
                    .postTitle(id)
                    .postContent("password is " + password)
                    .password(password.toString())
                    .build());
        }
        
        Iterable<Post> saveRes = postInitService.saveAll(list);
        
        Iterator<Post> iterator = saveRes.iterator();
        List<PostDTO> retVals = new ArrayList<>();
        
        while(iterator.hasNext()) {
            Post post = iterator.next();
            PostDTO vo = new PostDTO();
            vo.setPostId(post.getPostId());
            vo.setPostTitle(post.getPostTitle());
            vo.setPassword(post.getPassword());
            vo.setPostContent(post.getPostContent());
            vo.setCreatedTime(post.getCreatedTime());
            vo.setWriterIP(post.getWriterIP());
            vo.setLastModifiedTime(post.getModifiedTime());
            vo.setModifierIP(post.getModifierIP());
            retVals.add(vo);
        }
        return retVals;
    }
}
