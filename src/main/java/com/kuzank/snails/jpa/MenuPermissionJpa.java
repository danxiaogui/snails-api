package com.kuzank.snails.jpa;

import com.kuzank.snails.model.MenuPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/14
 */
@Repository
public interface MenuPermissionJpa extends JpaRepository<MenuPermission, String>, JpaSpecificationExecutor {
}