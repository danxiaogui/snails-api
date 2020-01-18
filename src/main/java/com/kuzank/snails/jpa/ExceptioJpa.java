package com.kuzank.snails.jpa;

import com.kuzank.snails.model.Exceptio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/13
 */
@Repository
public interface ExceptioJpa extends JpaRepository<Exceptio, String>, JpaSpecificationExecutor {
}