package com.kuzank.snails.jpa;

import com.kuzank.snails.model.SelectItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/14
 */
@Repository
public interface SelectItemJpa extends JpaRepository<SelectItem, String>, JpaSpecificationExecutor {

    List<SelectItem> findAllByOrderBySorttAsc();

    List<SelectItem> findAllByPidOrderBySorttAsc(String pid);

    @Transactional
    @Modifying
    int deleteByPid(String pid);

    @Transactional
    @Modifying
    int deleteByIdpathLike(String id);
}