# eGovFramework 4.1.0 Logging

## 개요
- OOP를 보완하는 개념
- 횡단관심(다수 객체들의 공통 관심사) 모듈화 처리를 지원하는 프로그래밍 기법
- Join Point: 횡단 관심 모듈이 삽입되어 동작할 수 있는 실행 가능한 특정 위치
- Pointcut: 어떤 클래스의 어느 JoinPoint를 사용할 것인지 결정
- Aspect: 애플리케이션이 가지고 있어야 할 로직과 실행 시점을 정의한 것
- Advice: 특정 Join Point 동작 시점에 실행하는 코드
- Weaving: Pointcut에 의해 결정된 JoinPoint에 지정된 Advice를 삽입하는 과정

참고: [@AspectJ 어노테이션을 이용한 AOP 지원](https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte4.1:fdl:aop:aspectj)

## Aspect
```xml
<bean id="aspect"><!-- Aspect class를 bean으로 등록 --></bean>
<aop:config>
    <!-- 코드를 실행할 시점인 Pointcut 지정 -->
    <aop:pointcut id="targetMethod" expression="execution(* com.zzong..*Controller.*(..))"/>
    
    <!-- 참조할 Aspect 빈 지정 -->
    <aop:aspect ref="aspect">
        <!-- Pointcut에 실행할 Advice 코드 작성 -->
        <aop:before method="beforeTarget" pointcut-ref="targetMethod"/>
        <aop:around method="aroundTarget" pointcut-ref="targetMethod"/>
        <aop:after method="afterTarget" pointcut-ref="targetMethod"/>
        <aop:after-returning method="afterReturningTarget" returning="retVal" pointcut-ref="targetMethod"/>
        <aop:after-throwing method="afterThrowingTarget" throwing="exception" pointcut-ref="targetMethod"/>
    </aop:aspect>
</aop:config>
```

### 실행 순서
> @Before > @Around > @After > @AfterReturning

## @EnableAspectJAutoProxy
> Application class에 @EnableAspectJAutoProxy 추가 및 @Aspect class에 @Component annotation을 추가하여 빈으로 등록

- proxyTargetClass = {true | false(default)}
  - true일 때 서브클래스 기반 프록시 생성 (CGLIB 프록시 사용, 클래스 기반)
- exposeProxy = {true | false(default)}
  - true일 때 대상객체가 현재의 프록시에 접근할 수 있도록 현재 프록시를 ThreadLocal에 노출되어 AopContext.currentProxy() 메서드로 검색이 가능
