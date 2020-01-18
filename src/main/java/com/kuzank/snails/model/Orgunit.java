package com.kuzank.snails.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_orgunit")
public class Orgunit extends Base {

    private String charge;
    private String pid;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String idpath;

    public static Orgunit of(String id, String title, String charge, Orgunit parent) {
        Orgunit obj = new Orgunit();
        obj.setId(id);
        obj.setTitle(title);
        obj.setCharge(charge);
        if (parent == null) {
            obj.setIdpath(id);
        } else {
            obj.setPid(parent.getId());
            obj.setIdpath(parent.getIdpath() + "," + id);
        }
        return obj;
    }
}
