package com.kuzank.snails.jpa;

import com.kuzank.snails.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/3
 */
@Repository
public interface AccountJpa extends JpaRepository<Account, String> {
}