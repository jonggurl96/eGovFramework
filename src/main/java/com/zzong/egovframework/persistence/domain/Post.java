package com.zzong.egovframework.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Post extends BaseEntity {
    
    @Id
    private String postId;
    
    @Column(nullable = false)
    private String postTitle;
    
    private String password;
    
    @Column(nullable = false)
    private String postContent;
    
//    private String attachedFile;
}
