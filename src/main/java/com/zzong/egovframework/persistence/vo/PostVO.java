package com.zzong.egovframework.persistence.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Alias("post")
public class PostVO {
    /**
     *
     * PostVO - myBatis로 DB와 연동될 VO
     *
     * @author jonggurl
     * @version 1.0.0
     * 작성일 2023-06-28, 수, 12:27
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
}
