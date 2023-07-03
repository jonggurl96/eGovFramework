package com.zzong.egovframework.cmmn.logging;

import org.egovframe.rte.fdl.logging.db.EgovConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class JDBCLogConfig {
    
    @Resource(name = "dataSource")
    private DataSource dataSource;
    
    @Bean("egovConnectionFactory")
    public EgovConnectionFactory egovConnectionFactory() {
        EgovConnectionFactory egovConnectionFactory = new EgovConnectionFactory();
        egovConnectionFactory.setDataSource(dataSource);
        return egovConnectionFactory;
    }
}
