package com.kuzank.snails.jpa;

import com.kuzank.snails.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/14
 */
@Repository
public interface MenuJpa extends JpaRepository<Menu, String>, JpaSpecificationExecutor {

    @Transactional
    @Modifying
    void deleteByIdpathLike(String id);
}