package com.kuzank.snails.jpa;

import com.kuzank.snails.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/14
 */
@Repository
public interface MenuJpa extends JpaRepository<Menu, String>, JpaSpecificationExecutor {

    List<Menu> findAllByOrderBySorttAsc();

    @Transactional
    @Modifying
    void deleteByIdpathLike(String id);

    @Query(value = "SELECT u0.* FROM sys_menu u0\n" +
            "WHERE EXISTS (\n" +
            "  SELECT ux.id FROM sys_permission ux\n" +
            "  WHERE (\n" +
            "    CASE\n" +
            "    WHEN ( CASE WHEN u0.inherit = 'yes' THEN u0.idpath ELSE u0.id END ) LIKE CONCAT( '%', ux.resource, '%' )  AND ux.target LIKE CONCAT( '%', ?1 , '%' ) THEN 1\n" +
            "    WHEN EXISTS (\n" +
            "        SELECT ur.id FROM sys_person ur INNER JOIN sys_orgunit uo ON ur.orgunit = uo.id\n" +
            "        WHERE uo.idpath LIKE CONCAT( '%', ux.target, '%' ) AND ur.id = ?1 \n" +
            "      )\n" +
            "      AND ( CASE WHEN u0.inherit = 'yes' THEN u0.idpath ELSE u0.id END ) LIKE CONCAT( '%', ux.resource, '%' ) THEN 1\n" +
            "    ELSE 0 END ) = 1\n" +
            "    AND u0.idpath LIKE CONCAT( '%', ux.resource, '%' )\n" +
            "  )\n" +
            "  ORDER BY LENGTH( u0.idpath ) ASC,u0.sortt ASC", nativeQuery = true)
    List<Menu> getByPerson(String personid);


    @Query(value = "SELECT u0.* FROM sys_menu u0\n" +
            "WHERE EXISTS (\n" +
            "  SELECT ux.id FROM sys_permission ux\n" +
            "  WHERE (\n" +
            "    CASE \n" +
            "    WHEN EXISTS (SELECT uo.id from sys_orgunit uo WHERE uo.idpath LIKE CONCAT( '%', ux.target, '%' ) and uo.id = ?1)\n" +
            "      AND ( CASE WHEN u0.inherit = 'yes' THEN u0.idpath ELSE u0.id END ) LIKE CONCAT( '%', ux.resource, '%' ) THEN 1\n" +
            "    ELSE 0 END ) = 1\n" +
            "    AND u0.idpath LIKE CONCAT( '%', ux.resource, '%' )\n" +
            "  )\n" +
            " ORDER BY LENGTH( u0.idpath ) ASC,u0.sortt ASC", nativeQuery = true)
    List<Menu> getByOrgunit(String orgunitid);
}