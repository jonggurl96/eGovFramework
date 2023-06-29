package com.zzong.egovframework.cmmn.idgnr;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.impl.EgovUUIdGnrServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.security.NoSuchAlgorithmException;

@Configuration
@PropertySource(value = {"classpath:/cmmn/idgnr/idgnr.yml"})
public class IdGenerationConfig {
    
    @Value("${uuid.generatedBy.ipAddress}")
    private String address;
    
    @Bean(name = "uuidGenerator")
    public EgovUUIdGnrServiceImpl egovUUIdGnrService() throws FdlException, NoSuchAlgorithmException {
        EgovUUIdGnrServiceImpl egovUUIdGnrService = new EgovUUIdGnrServiceImpl();
        egovUUIdGnrService.setAddress(address);
        return egovUUIdGnrService;
    }
}
