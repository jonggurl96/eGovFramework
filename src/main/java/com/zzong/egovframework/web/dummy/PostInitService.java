package com.zzong.egovframework.web.dummy;

import com.zzong.egovframework.persistence.domain.Post;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * PostInitService - Post table 초기 데이터 Spring Data JPA CRUD
 *
 * @author jonggurl
 * @version 1.0.0
 * 작성일 2023-06-27, 화, 15:28
 */
public interface PostInitService extends CrudRepository<Post, String> {
}
