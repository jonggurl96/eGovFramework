package com.zzong.egovframework.persistence.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.egovframe.rte.psl.dataaccess.mapper.MapperConfigurer;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;


@Configuration
public class MybatisConfigurer {
    
    @Bean("sqlSession")
    @Resource(name = "dataSource")
    public SqlSessionFactoryBean sqlSessionFactoryBean(HikariDataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("persistence/mybatis/mybatis_config.xml"));
        
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }
    
    @Bean
    public MapperConfigurer mapperConfigurer() {
        MapperConfigurer mapperConfigurer = new MapperConfigurer();
        mapperConfigurer.setBasePackage("com.zzong.egovframework.persistence.repository");
        return mapperConfigurer;
    }
}
