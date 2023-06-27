package com.zzong.egovframework.cmmn.idgnr;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.impl.EgovUUIdGnrServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Component
public class UUIdGenerator {
    
    @Resource(name = "uuidGenerator")
    private EgovUUIdGnrServiceImpl egovUUIdGnrService;
    
    public String getStringId() throws FdlException {
        return egovUUIdGnrService.getNextStringId();
    }
    
    public BigDecimal getBigDecimalId() throws FdlException {
        return egovUUIdGnrService.getNextBigDecimalId();
    }
}
