package com.kuzank.snails.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 人员
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_person")
public class Person extends Base {

    private String orgunit;
    private String email;
    private String phone;
    private String wechat;
    private String idcard;
    private String address;
    private String avatar;
    private String gender;
    private String birthdate;

    public enum Gender {

        Man("Man"),
        Woman("Woman");

        private String name;

        Gender(String name) {
            this.name = name;
        }

        public String value() {
            return name;
        }
    }

    public static Person of(String id, String name, String orgunit) {
        Person obj = new Person();
        obj.setId(id);
        obj.setTitle(name);
        obj.setOrgunit(orgunit);
        return obj;
    }
}
