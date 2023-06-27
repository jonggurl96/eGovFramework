package com.zzong.egovframework.persistence.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostVO {
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
