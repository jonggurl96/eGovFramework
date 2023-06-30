package com.zzong.egovframework.cmmn.logging;

import com.zaxxer.hikari.HikariDataSource;
import org.egovframe.rte.fdl.logging.db.EgovConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class JDBCLogConfig {
    
    @Resource(name = "dataSource")
    private HikariDataSource dataSource;
    
    @Bean("egovConnectionFactory")
    public EgovConnectionFactory egovConnectionFactory() {
        EgovConnectionFactory egovConnectionFactory = new EgovConnectionFactory();
        egovConnectionFactory.setDataSource(dataSource);
        return egovConnectionFactory;
    }
}
