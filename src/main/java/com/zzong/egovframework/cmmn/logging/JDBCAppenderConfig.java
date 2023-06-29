package com.zzong.egovframework.cmmn.logging;

import org.egovframe.rte.fdl.logging.db.EgovConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class JDBCAppenderConfig {
    
    @Resource(name = "dataSource")
    private DataSource dataSource;
    
    @Bean("egovConnFactory")
    public EgovConnectionFactory egovConnectionFactory() {
        EgovConnectionFactory bean = new EgovConnectionFactory();
        bean.setDataSource(dataSource);
        return bean;
    }
}
