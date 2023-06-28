package com.zzong.egovframework.persistence.service;

import com.zzong.egovframework.persistence.dto.PostDTO;

import java.util.List;

public interface PostService {
    public List<PostDTO> getAll(String title);
}
