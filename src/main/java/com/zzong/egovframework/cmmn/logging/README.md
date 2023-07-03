# eGovFramework 4.1.0 Logging
![](https://img.shields.io/badge/log4j2-2.17.2-brightgreen?style=plastic)

> [Log4j2](https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte3:fdl:logging:log4j_2:%EC%84%A4%EC%A0%95_%ED%8C%8C%EC%9D%BC%EC%9D%84_%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94_%EB%B0%A9%EB%B2%95)
> - 로깅 환경 설정 지원
> - 로그 기록 

## pom.xml
> org.egovframe.rte.fdl.dataaccess 의존성 추가한 경우 포함되어 있음
```xml
<dependency>
  <groupId>org.egovframe.rte</groupId>
  <artifactId>org.egovframe.rte.fdl.logging</artifactId>
  <version>${org.egovframe.rte.version}</version>
</dependency>
```
> SLF4J 충돌 시 spring-boot-starter-web에서 spring-boot-starter-logging 제외
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
  <exclusions>
    <exclusion>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-logging</artifactId>
    </exclusion>
  </exclusions>
</dependency>
```


## Log4j 환경설정 방법
- 설정 파일
  - WEB-INF/classes/log4j2.xml
  - 최상위 요소 `<Configuration>` 아래에 다음과 같은 구조를 갖는다.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration>

  <!-- Appender, Layout 설정 -->
  <Appenders>
    <Console name="console" target="SYSTEM_OUT">
      <PatternLayout/>
    </Console>
    <File name="file" fileName="./logs/file/sample.log" append="false">
      <PatternLayout pattern="%d %5p [%c] %m%n"/>
    </File>
  </Appenders>

  <!-- Logger 설정 -->
  <Loggers>
    <Logger name="egovLogger" level="DEBUG" additivity="false">
      <AppenderRef ref="console"/>
      <AppenderRef ref="file"/>
    </Logger>
    <Root level="ERROR">
        <AppenderRef ref="console"/>
    </Root>
    </Loggers>

</Configuration>
```

SpringBoot 실행 시 Web Application Context 구동 전에 파일을 읽게 됨

JDBC Appender를 사용하는데 EgovConnectionFactory bean이 필요한데 bean이 정의되기 전이라 에러가 발생함

따라서 log4j2.xml 파일을 log4j2.yml 파일로 변환 후 application.yml 파일에서 import 하겠음
```yaml
logging:
  config: classpath:cmmn/logging.log4j2.yml
```
- 의존성 추가 yml - json 변환
```xml
<dependency>
  <groupId>com.fasterxml.jackson.dataformat</groupId>
  <artifactId>jackson-dataformat-yaml</artifactId>
</dependency>
```
- 의존성 추가 Slf4j 구현체 설정 - `@Slf4j 사용 가능`
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

- Logger 호출

```java
package foo.bar;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Slf4j
public class Clazz {
  /**
   * Logger1과 Logger2는 Logger Name이 "foo.bar.Clazz"
   */
  Logger logger1 = LogManager.getLogger();
  Logger logger2 = LogManager.getLogger("foo.bar.Clazz");

  /**
   * Logger3 Logger Name은 "X"
   */
  Logger logger3 = LogManager.getLogger("X");

  /**
   * Slf4j 구현체 설정 의존성 추가 후 @Slf4j 사용
   * logger1, logger2와 같음
   */
  log.info("log");
}
```

## 중요 컴포넌트
- Logger
  - 로그의 주체, 애플리케이션 별 로거를 정의하고 이에 대한 로그 레벨과 Appender를 지정할 수 있음
- Appender
  - 로그를 출력하는 위치
- Layout
  - Appender의 출력 포맷 - 일자, 시간, 클래스 명 등 여러가지 정보를 선택해 지정할 수 있음
  - DateLayout, HTMLLayout, **PatternLayout**, SimpleLayout, XMLLayout이 있다.
  - PatternLayout ex) %d{HH:mm:ss.SSS} \[%thread\] %-5level %logger{36} - %msg%n

PatternLayout - %로 시작하고 % 뒤에는 format modifiers와 conversion character로 정의한다.

| 패턴 레이아웃         | note                                        |
|-----------------|---------------------------------------------|
| c, logger       | logger의 이름                                  |
| C, class        | 로깅 이벤트 발생 클래스의 FQN                          |
| M, method       | 로깅 이벤트 발생 메서드명                              |
| F, file         | 로깅 이벤트 발생 클래스의 파일명                          |
| l, location     | 로깅 이벤트가 발생한 클래스의 풀네임명.메서드명.(파일명:라인번호)       |
| d, date         | 로깅 이벤트 발생 일시를 SimpleDateFormat에 정의된 패턴으로 출력 |
| L, line         | 로깅 이벤트가 발생한 라인 번호                           |
| m, msg, message | 로그문에서 전달된 메시지 출력                            |
| n               | 개행                                          |
| p, level        | 로깅 이벤트의 레벨                                  |
| r, relative     | 로그 처리시간 milliseconds                        |
| t, thread       | 로깅 이벤트 발생 스레드명                              |

## 로그 레벨
- Fatal
  - 에러 중 가장 심각한 부분이나 운영 중엔 볼 일 없음
- Error
  - 요청 처리 중 문제가 발생한 상태
- Warn
  - 처리 가능한 문제, 경고성 메시지
- Info
  - 정보성 메시지
- Debug
  - 디버그 용도로 사용할 메시지
- Trace
  - 디버그 레벨이 너무 광범위한 것을 해결하기 위해 좀 더 상세한 상태를 나타냄
> logger의 level을 지정한 경우 지정한 level 미만의 이벤트는 무시

## Appender
> Layout의 종류
> - PatternLayout
> - HTMLLayout
> - RFC5424Layout
> - SerializedLayout
> - SyslogLayout
> - XMLLayout

- log4j.xml에 아래 Appender 들을 정의한다.

### Appenders
`log4j2.xml`의 `<Appenders>` 아래에 추가

#### ConsoleAppender
- attribute
  - name: Appender명
  - target: 출력방향 지정 {SYSTEM_OUT(default) | SYSTEM_ERR}
  - follow: 출력 변경사항 적용 여부
  - ignoreExceptions: default true
- element
  - Layout
  - Filters

```xml
<Console name="console">
  <!-- default pattern : %d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n -->
  <PatternLayout/>
</Console>
```

### FileAppender
- attribute
  - name
  - fileName: 로그를 저장할 파일 경로와 이름
  - append: 이어쓰기 여부 default true, false 덮어쓰기
  - locking: default false
  - immediateFlush: default true
  - ignoreExceptions: default true
  - bufferedIO: default true
  - bufferSizeStr: default 8192
- element
  - Layout
  - Filters

```xml
<File name="file" fileName="./logs/sample.log">
  <PatternLayout pattern="%d %5p [%c] %m%n"/>
</File>
```

### RollingFileAppender
- attribute
  - FileAppender와 동일
  - filePattern: history 파일명
- element
  - Layout
  - Filters
  - Policy: file rolling 조건 설정, TriggeringPolicy 구현체
  - Strategy: file name과 location 설정, RolloverStrategy 구현체

```xml
<RollingFile name="rolling" fileName="./logs/sample.log" filePattern="./logs/sample.%i.log">
  <PatternLayout pattern="%d %5p [%c] %m%n"/>
  <Policies>
    <!-- size 단위: Byte(default), KB, MB, GB -->
    <SizeBasedTriggeringPolicy size="1000"/>
    
    <!-- min(default 1)부터 max(default 7)까지 파일 생성 index가 작을수록 최신 -->
    <DefaultRolloverStrategy max="3" fileIndex="min"/>
  </Policies>
</RollingFile>
```

### JDBCAppender
> 로그를 RDB에 출력하기 위한 Appender,   
> EgovConnectionFactory 빈과 ConnectionFactory가 필요함
> ```xml
> <bean id="egovConnectionFactory" class="org.egovframe.rte.fdl.logging.db.EgovConnectionFactory">
>   <param name="dataSource" ref="dataSource"/>
> </bean>
> ```
- attribute
  - name
  - tableName: RDB 테이블 명
  - filter
  - bufferSize
  - ignoreExceptions
- element
  - ConnectionFactory
  - Column: pattern과 칼럼명 매핑

```xml
<JDBC name="db" tableName="db_log">
  <ConnectionFactory class="org.egovframe.rte.fdl.logging.db.EgovConnectionFactory" method="getDatabaseConnection"/>
  <Column name="eventDate" isEventTimestamp="true"/>
  <Column name="level" pattern="%p"/>
  <Column name="logger" pattern="%c"/>
  <Column name="message" pattern="%m"/>
  <Column name="exception" pattern="%ex{full}"/>
</JDBC>
```




