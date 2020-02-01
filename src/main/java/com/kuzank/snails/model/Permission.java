package com.kuzank.snails.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统权限，对资源权限进行限定
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_permission")
public class Permission extends Base {

    /**
     * 菜单ID、附件ID...
     */
    private String resource;
    /**
     * personid 、 orgunitid...
     */
    private String target;


    public static Permission of(String id, String resource, String target) {
        Permission obj = new Permission();
        obj.setId(id);
        obj.setResource(resource);
        obj.setTarget(target);

        return obj;
    }
}



