package com.zzong.egovframework.persistence.service.impl;

import com.zzong.egovframework.persistence.converter.entity2dto.PostConverter;
import com.zzong.egovframework.persistence.dto.PostDTO;
import com.zzong.egovframework.persistence.repository.PostJPARepository;
import com.zzong.egovframework.persistence.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postJPA")
@RequiredArgsConstructor
public class PostJPAServiceImpl implements PostService {
    
    private final PostJPARepository repository;
    
    @Override
    public List<PostDTO> getAll(String title) {
        return repository.findAllByPostTitleStartingWith(title).stream().map(PostConverter::toDto).toList();
    }
}
