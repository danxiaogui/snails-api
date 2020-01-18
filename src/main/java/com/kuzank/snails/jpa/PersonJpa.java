package com.kuzank.snails.jpa;

import com.kuzank.snails.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/3
 */
@Repository
public interface PersonJpa extends JpaRepository<Person, String>, JpaSpecificationExecutor {

    @Query("select a from Person a join Account u0 on a.id = u0.personid and u0.username = ?1 and u0.password = ?2")
    Optional<Person> findByUsernameAndPassword(String username, String password);

    @Query("select a from Person a join Account u0 on a.id = u0.personid and u0.username = ?1")
    Optional<Person> findByUsername(String username);

    List<Person> findAllByOrgunit(String orgunit);
}