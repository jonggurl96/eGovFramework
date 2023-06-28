package com.zzong.egovframework.persistence.repository;

import com.zzong.egovframework.persistence.vo.PostVO;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper("postMapper")
public interface PostMapper {
    public List<PostVO> selectAll(@Param("title") String title);
}
