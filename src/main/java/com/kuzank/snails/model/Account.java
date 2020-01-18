package com.kuzank.snails.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_account")
public class Account extends Base {

    private String personid;
    // 登陆账号 & 密码
    private String username;
    private String password;


    public static Account of(String id, String username, String password, String personid) {
        Account obj = new Account();
        obj.setId(id);
        obj.setUsername(username);
        obj.setPassword(password);
        obj.setPersonid(personid);
        return obj;
    }
}
