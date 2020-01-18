package com.kuzank.snails.jpa;

import com.kuzank.snails.model.HttpLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/10
 */
@Repository
public interface HttpLogJpa extends JpaRepository<HttpLog, String>, JpaSpecificationExecutor {
}