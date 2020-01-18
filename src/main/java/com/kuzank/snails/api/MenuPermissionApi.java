package com.kuzank.snails.api;

import com.kuzank.snails.core.Result;
import com.kuzank.snails.jpa.MenuJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/14
 */
@RestController
@RequestMapping("/api/menupermission")
public class MenuPermissionApi {

    @Autowired
    MenuJpa menuJpa;


    @GetMapping("/list")
    public Result list() {
        return Result.ofsuccess(menuJpa.findAll());
    }

}
