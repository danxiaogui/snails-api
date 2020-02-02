# 创建工程
```text
snails是一个基于 Spring-Boot + Angular + Ng-Zorro 前后端分离项目的简单实现，因此我们会创建两个项目，分别是：
1、snails-web：Angular + Ng-Zorro + Ng-Alain
2、snails-api：Sprint-Boot + JPA + lombok + Java8 + Mysql
以下是创建项目的过程。
```

## 1、创建前端工程

### 1.1 从 [ng-alain](https://github.com/ng-alain/ng-alain/) 克隆项目
```shell
➜ git clone git@github.com:ng-alain/ng-alain.git snails-web

Cloning into 'snails-web'...
remote: Enumerating objects: 211, done.
remote: Counting objects: 100% (211/211), done.
remote: Compressing objects: 100% (167/167), done.
Receiving objects:  49% (6351/12955), 49.92 MiB | 151.00 KiB/s
remote: Total 12955 (delta 61), reused 113 (delta 41), pack-reused 12744
Receiving objects: 100% (12955/12955), 80.09 MiB | 130.00 KiB/s, done.
Resolving deltas: 100% (8486/8486), done.
```

### 1.2 安装依赖

```shell
cd snails-web
npm install
```

### 1.3 启动项目

```shell
npm run start
```

### 1.4 浏览器访问 http://localhost:4200/
![](https://tva1.sinaimg.cn/large/006tNbRwgy1gakm4ky7x7j31h40u07wh.jpg)


## 2、创建后台工程

### 2.1 在 IDEA 上新建项目 
![](https://tva1.sinaimg.cn/large/006tNbRwgy1gakjky2ynbj30iy0cvt9r.jpg)

![](https://tva1.sinaimg.cn/large/006tNbRwgy1gakjlcyuyfj30iy0cuaax.jpg)

![](https://tva1.sinaimg.cn/large/006tNbRwgy1gakjpy3rwvj30po0fddhn.jpg)

![](https://tva1.sinaimg.cn/large/006tNbRwgy1gakjqme9buj30oa0feaay.jpg)

### 2.2 启动 SnailsApplication 
```shell script
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.2.RELEASE)

2020-01-04 15:20:07.669  INFO 81900 --- [  restartedMain] com.kuzank.snails.SnailsApplication      : Starting SnailsApplication on fanghaoengdeMBP with PID 81900 (/Users/kuzan/Documents/snails/snails-api/target/classes started by kuzan in /Users/kuzan/Documents/snails/snails-api)
2020-01-04 15:20:07.672  INFO 81900 --- [  restartedMain] com.kuzank.snails.SnailsApplication      : No active profile set, falling back to default profiles: default
2020-01-04 15:20:07.720  INFO 81900 --- [  restartedMain] o.s.b.devtools.restart.ChangeableUrls    : The Class-Path manifest attribute in /Users/kuzan/.m2/repository/org/glassfish/jaxb/jaxb-runtime/2.3.2/jaxb-runtime-2.3.2.jar referenced one or more files that do not exist: file:/Users/kuzan/.m2/repository/org/glassfish/jaxb/jaxb-runtime/2.3.2/jakarta.xml.bind-api-2.3.2.jar,file:/Users/kuzan/.m2/repository/org/glassfish/jaxb/jaxb-runtime/2.3.2/txw2-2.3.2.jar,file:/Users/kuzan/.m2/repository/org/glassfish/jaxb/jaxb-runtime/2.3.2/istack-commons-runtime-3.0.8.jar,file:/Users/kuzan/.m2/repository/org/glassfish/jaxb/jaxb-runtime/2.3.2/stax-ex-1.8.1.jar,file:/Users/kuzan/.m2/repository/org/glassfish/jaxb/jaxb-runtime/2.3.2/FastInfoset-1.2.16.jar,file:/Users/kuzan/.m2/repository/org/glassfish/jaxb/jaxb-runtime/2.3.2/jakarta.activation-api-1.2.1.jar
2020-01-04 15:20:07.720  INFO 81900 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2020-01-04 15:20:07.720  INFO 81900 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2020-01-04 15:20:08.512  INFO 81900 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2020-01-04 15:20:08.585  INFO 81900 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 61ms. Found 1 JPA repository interfaces.
2020-01-04 15:20:08.889  INFO 81900 --- [  restartedMain] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration' of type [org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2020-01-04 15:20:09.173  INFO 81900 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8081 (http)
2020-01-04 15:20:09.182  INFO 81900 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2020-01-04 15:20:09.182  INFO 81900 --- [  restartedMain] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.29]
2020-01-04 15:20:09.271  INFO 81900 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2020-01-04 15:20:09.271  INFO 81900 --- [  restartedMain] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1551 ms
2020-01-04 15:20:09.419  INFO 81900 --- [  restartedMain] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2020-01-04 15:20:09.495  INFO 81900 --- [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate Core {5.4.9.Final}
2020-01-04 15:20:09.659  INFO 81900 --- [  restartedMain] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.0.Final}
2020-01-04 15:20:09.748  INFO 81900 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2020-01-04 15:20:09.907  INFO 81900 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2020-01-04 15:20:09.920  INFO 81900 --- [  restartedMain] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.MySQL5InnoDBDialect
2020-01-04 15:20:10.537  INFO 81900 --- [  restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2020-01-04 15:20:10.542  INFO 81900 --- [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2020-01-04 15:20:10.570  INFO 81900 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2020-01-04 15:20:10.943  WARN 81900 --- [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2020-01-04 15:20:11.051  INFO 81900 --- [  restartedMain] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2020-01-04 15:20:11.294  INFO 81900 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8081 (http) with context path ''
2020-01-04 15:20:11.296  INFO 81900 --- [  restartedMain] com.kuzank.snails.SnailsApplication      : Started SnailsApplication in 4.047 seconds (JVM running for 4.874)
```

### 2.3 application.yml 配置
```yaml
server:
  port: 8081
spring:
  application:
    name: snails-api
  # mysql 属性配置
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/snails?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: 123456
  jpa:
    show-sql: false
    hibernate:
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    properties:
      hibernate:
        hbm2ddl:
          auto: update
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
```

### 2.4  pom.xml 配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.2.2.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.kuzank</groupId>
	<artifactId>snails</artifactId>
	<version>0.1</version>
	<name>snails</name>
	<description>Spring Boot Api For Snails Application</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-configuration-processor</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
			<version>3.9</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.6.0</version>
		</dependency>
		<dependency>
			<groupId>com.google.guava</groupId>
			<artifactId>guava</artifactId>
			<version>25.0-jre</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```
















