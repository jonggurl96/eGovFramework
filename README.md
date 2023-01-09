# eGovFramework
전자정부 프레임워크 시작하기

**package egovframework.example
package cpservice로 변경**
`단, resource, webapp 등의 폴더는 그대로`

# 동작 원리
실행 - web.xml - dispatcher-servlet.xml, context-*.xml 위치 설정

dispatcher-servlet.xml
---
context:component-scan, interceptor, renderer, UrlBasedViewResolver, SessionLocaleResolver 등이 있음.
JSON 객체 사용 시 빈 RequestMappingHandlerAdapter에 아래와 같이 property 추가
```
<proprety name="mappingJackson2HttpMessageConverter">
    <list>
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"/>
    </list>
</property>
```
# context-*.xmls
- context-aspect.xml: 관점 지향 context (주로 Exception Handler)
- context-common.xml: 각 언어별 문자열 상수 저장(message-common.properties)
- context-datasource.xml: JDBC DataSource 명시
    > 사용할 JDBC 빈 주석만 해제 후 url, id, pw 변경해 사용
- context-idgen.xml: 테이블 데이터 id 생성용
- context-mapper.xml: SqlSession 빈 설정 파일 iBatis 사용 시 MapperConfigurer 필요 없음
    - sql-mapper-config.xml: MyBatis 설정 파일 (mybatis-config.xml)
        ```
        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <property datasource>
            <property configLocation>
            <property mapperLocations>
        </bean>
        <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate" destroy-method="clearCache">
            <constructor-arg name=sqlSessionFactory" ref="sqlSessionFactory"/>
        </bean>
        ```
- context-properties.xml: pageUnit, pageSize와 같은 상수값
- context-sqlMap.xml: iBatis용 설정 파일
    - sql-map-config.xml:iBatis 설정 파일
- context-transaction.xml: 트랜잭션 관점 설정 (Exception 처리 등)
- context-validator.xml: validator 관련 설정들과 xml 파일 위치

# 프로젝트 생성 후 해야할 일
1. Java Compiler 버전 수정
2. Tomcat, Servlet 버전 수정
3. 패키지 명 변경: 이클립스에서 하다가 경로 꼬일 수 있으니 python 코드로 한 번에 해결

# Controller 경로 변경
web.xml 3개 url-pattern 변경
1. encodingFilter: /*
2. HTMLTagFilter: /*
3. action: /
3-1. 아래 코드 추가
    ```
    <servlet-mapping>
        <servlet-name>default</servlet-name>
        <url-pattern>*.js</url-pattern>
        <url-pattern>*.css</url-pattern>
        <url-pattern>*.jpg</url-pattern>
        <url-pattern>*.gif</url-pattern>
        <url-pattern>*.ico</url-pattern>
        <url-pattern>*.swf</url-pattern>
        <url-pattern>*.png</url-pattern>
    </servlet-mapping>
    ```
# @Controller와 @RestController
## @Controll
method들은 view의 이름을 return
```
@RequestMapping(value="/list", method=RequestMethod.GET)
public void list() throws Exception {
    // 설정파일 prefix / list.suffix로 자동으로 이동
}
@RequestMapping(value="/list", method=RequestMethod.GET)
public String list() throws Exception {
    return "redirect:/board/listAll";
    // url로 redirect
}
```
## @RestController
method들은 data와 ResponseEntity를 return
```
@RequestMapping(value="/board/{bno}", method=RequestMethod.GET)
public ResponseEntity<VO>list(@Variable("bno") int bno) {
    ResponseEntity<VO> entity = null;
    try {
        ...
        entity = new ResponseEntity<VO>(vo, HttpStatus.OK);
    } catch (Exception e) {
        entity = new ResponseEntity<VO>(e.printStackTrace(), HttpStatus.BAD_REQUEST);
    }
    return entity;
}
```



