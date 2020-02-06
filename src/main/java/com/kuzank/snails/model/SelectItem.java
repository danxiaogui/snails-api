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
 * @author kuzank 2020/2/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_selectitem")
public class SelectItem extends Base {

    @Column
    private String pid;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String idpath = "";
    /**
     * 是否可用 yes no
     */
    @Column(columnDefinition = "varchar(32) default 'yes'")
    private String enable = "yes";
    @Column
    private String sortt;


    public static SelectItem ofRoot(String id, String title, String sortt) {
        SelectItem obj = new SelectItem();
        obj.setId(id);
        obj.setIdpath(id);
        obj.setTitle(title);
        obj.setSortt(sortt);
        return obj;
    }

    public static SelectItem ofParent(String id, String title, SelectItem parent, String sortt) {
        SelectItem obj = new SelectItem();
        obj.setId(id);
        obj.setPid(null != parent ? parent.getId() : null);
        obj.setIdpath(null != parent ? parent.getIdpath() + "," + id : id);
        obj.setTitle(title);
        obj.setSortt(sortt);
        return obj;
    }

    /**
     * 是否
     */
    public enum Whether {

        whether("whether"),
        yes("yes"),
        no("no");

        private final String name;

        Whether(String name) {
            this.name = name;
        }

        public static boolean IsTrue(String obj) {
            return Whether.yes.getName().equals(obj);
        }

        public String getName() {
            return name;
        }
    }
}
