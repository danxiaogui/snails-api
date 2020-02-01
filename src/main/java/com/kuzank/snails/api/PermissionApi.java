package com.kuzank.snails.api;

import com.kuzank.snails.core.Result;
import com.kuzank.snails.jpa.PermissionJpa;
import com.kuzank.snails.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * <p>Description: 系统权限接口，包括菜单权限查询配置等</p>
 *
 * @author kuzank 2020/1/13
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionApi {

    @Autowired
    PermissionJpa permissionJpa;

    @GetMapping("/getByResource/{id}")
    public Result getByResource(@PathVariable String id) {
        return Result.ofsuccess(permissionJpa.getByResource(id));
    }

    @GetMapping("/allow/{resource}/{target}")
    public Result allow(@PathVariable String resource, @PathVariable String target) {
        Optional<Permission> optional = permissionJpa.findByResourceAndTarget(resource, target);
        if (!optional.isPresent()) {
            Permission permission = new Permission();
            permission.setResource(resource);
            permission.setTarget(target);
            permissionJpa.save(permission);
        }
        return Result.ofsuccess(true);
    }

    @GetMapping("/cancel/{resource}/{target}")
    public Result cancel(@PathVariable String resource, @PathVariable String target) {
        Optional<Permission> optional = permissionJpa.findByResourceAndTarget(resource, target);
        if (optional.isPresent()) {
            permissionJpa.deleteById(optional.get().getId());
        }
        return Result.ofsuccess(true);
    }
}
