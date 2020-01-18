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
@Table(name = "sys_menu_permission")
public class MenuPermission extends Base {

    private String menuid;
    /**
     * 用户ID、组织ID
     */
    private String target;

}



