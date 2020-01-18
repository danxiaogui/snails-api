package com.kuzank.snails.jpa;

import com.kuzank.snails.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/10
 */
@Repository
public interface LoginJpa extends JpaRepository<Login, String>, JpaSpecificationExecutor {

    Optional<Login> findByToken(String token);
}