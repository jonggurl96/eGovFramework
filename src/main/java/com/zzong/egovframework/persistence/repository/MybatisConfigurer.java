package com.zzong.egovframework.persistence.repository;

import org.egovframe.rte.psl.dataaccess.mapper.MapperConfigurer;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class MybatisConfigurer {
    
    @Bean("sqlSession")
    @Resource(name = "dataSource")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Value("${mybatis.config.classpath}") String mcc,
                                                       DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mcc));
        
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
