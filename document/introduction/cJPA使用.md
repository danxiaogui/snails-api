
# JPA使用
JPA全称为Java Persistence API ，Java持久化API是Sun公司在java EE 5规范中提出的Java持久化接口。JPA吸取了目前Java持久化技术的优点，旨在规范、简化Java对象的持久化工作。使用JPA持久化对象，并不是依赖于某一个ORM框架。

## 1、pom.xml 添加 JPA 依赖
```xml
<!-- jpa 依赖 -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<!-- mysql 依赖 -->
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<scope>runtime</scope>
</dependency>
<!-- lombok 依赖 -->
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
	<optional>true</optional>
</dependency>
```

## 2、在 Mysql 中新建一个数据库 
```sql
create database snails;
grant all privileges on snails.* to 'root'@'%' identified by '123456';
flush privileges;
```

## 3、在 application.yml 配置数据库连接
```yaml
spring:
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

## 4、建立一个基础实体类 Base
其中的 @Data、@MappedSuperclass 属于 Lombok 语法，程序会在编译源码期间生成相应的 getter、setter方法等，注意 IDEA 要先安装 Lombok Plugin 插件，并且在 pom.xml 中添加 lombok 依赖

其他实体类会集成 Base 类，以便得到相同的字段【id、title、createtime、creator、updatetime、description】
```java
package com.kuzank.snails.model;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class Base {

    @Id
    @Column(length = 32)
    private String id = UUID.randomUUID().toString().replace("-", "");

    @Column
    private String title;

    @Column(length = 30)
    private String createtime = LocalDateTime.now().toString();

    @Column(length = 32)
    private String creator;

    @Column(length = 30)
    private String updatetime = LocalDateTime.now().toString();

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
}
```

## 5、建立一个用户实体类 Person
其中的 @Data、@MappedSuperclass 属于 Lombok 语法，程序会在编译源码期间生成相应的 getter、setter方法等，注意 IDEA 要先安装 Lombok Plugin 插件，并且在 pom.xml 中添加 lombok 依赖
```java
package com.kuzank.snails.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_person")
public class Person extends Base {

    private String email;
    private String phone;
    private String wechat;
    private String idcard;
    private String address;
    private String avatar;
    private String gender;
    private String birthdate;
    // 登陆账号 & 密码
    private String username;
    private String password;
}
```

## 6、实现一个持久层服务
PersonJpa 继承 JpaRepository 和 JpaSpecificationExecutor 后，默认会得到以下的方法
![](https://tva1.sinaimg.cn/large/006tNbRwgy1gakkwwzb4hj30fa062wf3.jpg)
![](https://tva1.sinaimg.cn/large/006tNbRwgy1gakkxl9u06j30f7067mxt.jpg)
![](https://tva1.sinaimg.cn/large/006tNbRwgy1gakkykik57j30fc078q3p.jpg)
```java
package com.kuzank.snails.jpa;

import com.kuzank.snails.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonJpa extends JpaRepository<Person, String>, JpaSpecificationExecutor {
}
```

## 7、新建 PersonJpaTest 测试类
```java
package com.kuzank.snails.jpa;

import com.kuzank.snails.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonJpaTest {

    @Autowired
    PersonJpa personJpa;

    @Test
    public void create() {
        Person person = new Person();
        person.setId("1");
        person.setTitle("kuzank");

        personJpa.save(person);
    }

    @Test
    public void edit() {
        Person person = new Person();
        person.setId("1");
        person.setTitle("kuzank-edit");

        personJpa.save(person);
    }

    @Test
    public void delete() {
        personJpa.deleteById("1");
    }
}
```

## 8、运行测试类后检查数据库
![](https://tva1.sinaimg.cn/large/006tNbRwgy1gakl45h5oij30cc0doq4g.jpg)


## JPA 使用参考
- [SpringBoot 中 JPA 的使用](https://www.jianshu.com/p/c14640b63653)

