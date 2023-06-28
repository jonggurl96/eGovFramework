package com.zzong.egovframework.persistence.converter.entity2dto;

import com.zzong.egovframework.persistence.domain.Post;
import com.zzong.egovframework.persistence.dto.PostDTO;

public class PostConverter {
    
    public static PostDTO toDto(Post entity) {
        return new PostDTO(entity.getPostId(),
                entity.getPostTitle(), entity.getPassword(), entity.getPostContent(),
                entity.getCreatedTime(), entity.getModifiedTime(), entity.getWriterIP(), entity.getModifierIP());
    }
    
    public static Post toEntity(PostDTO dto) {
        return Post.builder()
                .postId(dto.getPostId())
                .postTitle(dto.getPostTitle())
                .postContent(dto.getPostContent())
                .password(dto.getPassword())
                .createdTime(dto.getCreatedTime())
                .modifiedTime(dto.getLastModifiedTime())
                .writerIP(dto.getWriterIP())
                .modifierIP(dto.getModifierIP())
                .build();
    }
}
