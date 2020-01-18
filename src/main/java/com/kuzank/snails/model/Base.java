package com.kuzank.snails.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author aires
 */
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
