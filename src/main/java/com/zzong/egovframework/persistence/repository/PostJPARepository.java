package com.zzong.egovframework.persistence.repository;

import com.zzong.egovframework.persistence.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJPARepository extends JpaRepository<Post, String> {
    public List<Post> findAllByPostTitleStartingWith(String Kwrd);
}
