# eGovFramework 4.1.0 ID Generation
> pom.xml
> ```xml
> <dependency>
>   <groupId>org.egovframe.rte</groupId>
>   <artifactId>org.egovframe.rte.fdl.idgnr</artifactId>
>   <version>${org.egovframe.rte.version}</version>
> </dependency>
> ```


## UUID Generation
> bean class = org.egovframe.rte.fdl.idgnr.impl.EgovUUIdGnrServiceImpl
> - ~~EgovUUIdGnrService deprecated~~

#### address 속성값 부여
- IP 기반 생성
- MAC 기반 생성

#### address 속성값 미부여
- Math.random() 기반 생성

## Sequence ID Generagion
> Database의 SEQUENCE 사용

#### Long Type, BigDecimal Type Service
> `CREATE SEQUENCE seq MINVALUE 0;`   
> bean class = org.egovframe.rte.fdl.idgnr.impl.EgovSequenceIdGnrSereviceImpl
- property
  - name = dataSource, ref = dataSource
  - name = query, value = "SELECT seq.NEXTVAL FROM DUAL"
- example
  - `egovSequenceIdGenerator.getNextLongIdInner();`
  - `egovSequenceIdGenerator.getNextBigDecimalIdInner();`

## Table ID Generation
> 새 ID를 얻기 위한 별도 테이블 작성

#### Schema
```sql
CREATE TABLE ids ( table_name varchar(16) NOT NULL,
                    next_id DECIMAL(30) NOT NULL,
                    PRIMARY KEY (table_name));
INSERT INTO ids VALUES('id','0');
```

#### Bean
- class = org.egovframe.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl
- destroy-method = destroy 

`property`

| name       | value           | note                            |
|------------|-----------------|---------------------------------|
| dataSource | ref: dataSource | dataSource                      |
| blockSize  | 10              | DB 접속 간격                        |
| table      | ids             | 생성하는 테이블 정보                     |
| tableName  | id              | 아이디 개별 인식을 위한 키 값               |
| strategy   | ref: strategy   | EgovIdGnrStrategyImpl bean name |

#### Strategy Bean (Optional)
- class = org.egovframe.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl

`property`

| name     | value  | note            |
|----------|--------|-----------------|
| prefix   | TEST-  | ID의 접두사         |
| cipers   | 5      | prefix를 제외한 길이  |
| fillChar | *      | ID의 빈 자리를 채울 문자 |
