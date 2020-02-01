package com.kuzank.snails.jpa;

import com.kuzank.snails.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/3
 */
@Repository
public interface PermissionJpa extends JpaRepository<Permission, String> {

    List<Permission> getByResource(String resource);

    Optional<Permission> findByResourceAndTarget(String resource, String target);
}