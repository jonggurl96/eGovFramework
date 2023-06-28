package com.zzong.egovframework.persistence.dto;

import com.zzong.egovframework.persistence.vo.PostVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    /**
     *
     * PostDTO - Presentation Layer에 노출될 DTO
     *
     * @author jonggurl
     * @version 1.0.0
     * 작성일 2023-06-28, 수, 12:28
     */
    private String postId;
    
    private String postTitle;
    
    private String password;
    
    private String postContent;

//    private String attachedFile;
    
    private LocalDateTime createdTime;
    
    private LocalDateTime lastModifiedTime;
    
    private String writerIP;
    
    private String modifierIP;
    
    public static PostDTO vo2dto(PostVO vo) {
        return new PostDTO(vo.getPostId(),
                vo.getPostTitle(),
                vo.getPassword(),
                vo.getPostContent(),
                vo.getCreatedTime(),
                vo.getLastModifiedTime(),
                vo.getWriterIP(),
                vo.getModifierIP());
    }
}
