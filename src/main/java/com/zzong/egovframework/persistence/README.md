# eGovFramework 4.1.0 Persistence Layer

## DataSource
> DataSource 객체 획득 방법
> - Spring DriverManagerDataSource
>   ```xml
>   <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
>       <property name="driverClassName" value="${driver}" />
>       <property name="url" value ="${url}" />
>       <property name="username" value="${username}" />
>       <property name="password" value="${password}" />
>   </bean>
>   ```
> - DBCP DataSource
>   ```xml
>   <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close">
>       <property name="driverClassName" value="${driver}" />
>       <property name="url" value ="${url}" />
>       <property name="username" value="${username}" />
>       <property name="password" value="${password}" />
>   
>       <!-- 최초 Connection Pool에 채워 넣을 Connection 수 -->
>       <property name="initialSize" value="0" />
>   
>       <!-- 동시에 사용 가능한 Connection 수 >= initialSize -->
>       <property name="maxTotal" value="8" />
>   
>       <!-- Connection Pool에 반납 시 최대로 유지될 수 있는 연결 개수 = maxTotal -->
>       <property name="maxIdle" value="8" />
>   
>       <!-- Connection Pool에서 최소한으로 유지할 Connection 수 -->
>       <property name="minIdle" value="0" />
>   
>       <!-- 연결 가능한 Connection이 없을 때 반납되는 Connection이 대기하는 시간(ms) -->
>       <property name="maxWaitMillis" value="-1" />
>   </bean>
>   ```
> - C3P0 DataSource
>   ```xml
>   <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource" destroy-method="close">
>       <property name="driverClassName" value="${driver}" />
>       <property name="url" value ="${url}" />
>       <property name="username" value="${username}" />
>       <property name="password" value="${password}" />
>   
>       <!-- 최초 Connection Pool에 채워 넣을 Connection 수 -->
>       <property name="initialPoolSize" value="3" />
>   
>       <!-- 동시에 사용 가능한 Connection 수 -->
>       <property name="maxPoolSize" value="15" />
>       <property name="minPoolSize" value="3" />
>   
>       <!-- 기존 연결 소진 시 새로 획득하려고 시도하는 연결 수 -->
>       <property name="acquireIncrement" value="3" />
>   
>       <!-- Connection 수명 -->
>       <property name="maxConnectionAge" value="0" />
>   
>       <!-- 연결된 상태로 유지될 수 있는 시간(s) -->
>       <property name="maxIdleTime" value="0" />
>   
>       <!-- minPoolSize를 초과한 연결이 폐기되기 전 풀에서 유지될 수 있도록 허용한 시간 (s) -->
>       <property name="maxIdleTimeExcessConnections" value="0" />
>   </bean>
>   ```
> - JNDI(Java Naming and Directory Interface) DataSource
>   - 디렉터리 서비스에서 제공하는 데이터 및 객체를 찾아 참고하기 위한 자바 API
>   - JNDI Lookup을 이용하여 WAS에서 제공되는 JNDI tree로부터 DataSource를 가져옴
>   - WAS에서 미리 설정된 연결정보를 호출하여 접근하는 방식
- SpringBoot는 DataSource를 자동으로 HikariCP를 사용하는 HikariDataSource타입의 dataSource 빈으로 등록한다.

## Data Access - MyBatis
> MyBatis
> - MapperConfig XML File: MyBatis 동작을 위한 공통 설정을 정의
> - Mapper XML File: 실행할 SQL문 및 매핑 정보를 XML 방식으로 정의

> eGovFramework 권장사항대로 @Mapper annotation 사용

`pom.xml`
```xml
<dependencies>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.3.1</version>
    </dependency>
    <dependency>
        <groupId>org.egovframe.rte</groupId>
        <artifactId>org.egovframe.rte.psl.dataaccess</artifactId>
        <version>${org.egovframe.rte.version}</version>
    </dependency>
</dependencies>
```

### Mapper Configuration XML 파일 작성
```xml
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
    <typeAliases>
        <typeAlias type="FQN" alias="alias" />
        또는 @Alias annotation으로 alias값을 직접 지정한 클래스의 패키지 명 지정
        <package name="@Alias 클래스의 패키지"/>
    </typeAliases>
    
    <!-- Mapper Location, SqlSessionFactoryBean에서 지정하지 않았다면 작성 -->
    <mappers>
        <mapper resource="META-INF/sqlmap/mappers/lab.xml" />
    </mappers>
</configuration>
```

### SQL Mapper XML 파일 작성
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="@Mapper interface의 FQN">
    
    <!-- Result Object 맵핑 설정 -->
    <resultMap id="res" type="alias">
        <result property="id" column="db_id" />
    </resultMap>
    
    <!-- sql fragment 생성 -->
    <sql id="columns">DB_ID</sql>
    
    <!-- SQL query -->
    <select id="selectRes" parameterType="alias" resultMap="res">
        select <include refid="columns" />
        from DB_TABLE
        where DB_ID = #{id}
    </select>
    
</mapper>
```

### SqlSessionFactoryBean
```xml
<bean id="sqlSession" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <property name="configLocation" value="Mapper Config XML File 위치" />
    
    <!-- Mapper Config XML File에서 지정하지 않은 경우 작성 -->
    <property name="mapperLocations" value="SQL Mapper XML 파일들의 위치" />
</bean>
```

### @Mapper interface 작성
```java
@Mapper("mapper")
public interface Mapper {
    /**
     * @param obj 대신 alias
     * @return Object 대신 resultMap
     */
    public Object selectRes(Object obj);
}
```

### MapperConfigurer bean
> @Mapper interface 자동 스캔 및 동작
```xml
<bean class="org.egovframe.rte.fdl.psl.dataaccess.mapper.MapperConfigurer">
    <property name="basePackage" value="Mapper interface가 속한 패키지 지정" />
</bean>
```


## Data Access - Spring Data JPA
> Spring Data 특징
> - Persistence Layer를 추상화하여 DB에 독립적
> - CRUD, Paging, Sorting을 지원
> - 런타임 시 구현체 생성

> JPA 특징
> - CRUD, Paging, Sorting을 지원하는 JPARepository<T, ID implements Serializable> 상속
> - @NoRepositoryBean -> @RequiredArgsConstructor 사용

### pom.xml
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### Query Method 정의
- 정해진 키워드에 따라 작성된 메서드 명으로부터 쿼리를 생성
- 지원하는 키워드
  - And, Or, Between
  - LessThan, GreaterThan
  - IsNull, (Is)NotNull
  - Like, NotLike
  - StartingWith, EndingWith, Containing
  - OrderBy
  - Not, In, NotIn, True, False


## Transaction
> Spring Transaction
- DataSource Transaction Service
  - DataSource의 defaultAutoCommit이 false일 때 Local Transaction 관리
    ```xml
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
      <property name="dataSource" ref="dataSource" />
    </bean>
    ```
- JTA Transaction Service
  - Java Transaction API를 이용하여 Global Transaction 관리
- JPA Transaction Service
  - JPA EntityManagerFactory를 이용해 트랜잭션을 관리
  - DataSource의 defaultAutoCommit = false
```xml
<beans>
  <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean" >
    <property name="persistenceUnitName" value="OraUnit" />
    <property name="persistenceXmlLocation" value="persistence.xml" />
    <property name="dataSource" ref="dataSource" />
  </bean>
  
  <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
    <property name="entityManagerFactory" ref="entityManagerFactory" />
  </bean>
</beans>
```

### Declarative Transaction Management
> Annotation 또는 XMl 정의를 이용해 Transaction 관리

#### Annotation   
`<tx:annotation-driven transaction-manager="transactionManager" />`
- @Transactional annotation
  - 트랜잭션 처리하고자 하는 메소드 위에 기재

#### XML
- aop config
```xml
<aop:config>
  <aop:pointcut id="requiredTx" expression="execution(* egovframework..*Impl.*(..))" />
  <aop:advisor advice-ref="txAdvice" pointcut-ref="requiredTx" />
</aop:config>
```

- tx:advice config
```xml
<tx:advice id="txAdvice" transaction-manager="transactionManager">
  <tx:attributes>
    <tx:method name="find*" read-only="true" />
    <tx:method name="createNoRBRole" no-rollback-for="NoRoleBackTx" />
    <tx:method name="createRBRole" rollback-for="RoleBackTx" />
    <tx:method name="create*" />
  </tx:attributes>
</tx:advice>
```

### Programmatic Transaction Management
생략ㅎ



