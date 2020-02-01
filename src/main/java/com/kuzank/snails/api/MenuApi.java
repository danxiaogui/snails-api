package com.kuzank.snails.api;

import com.kuzank.snails.core.Result;
import com.kuzank.snails.helper.SqlHelper;
import com.kuzank.snails.jpa.MenuJpa;
import com.kuzank.snails.jpa.PermissionJpa;
import com.kuzank.snails.jpa.PersonJpa;
import com.kuzank.snails.model.Menu;
import com.kuzank.snails.model.Person;
import com.kuzank.snails.service.IdentityService;
import com.kuzank.snails.service.MenuService;
import com.kuzank.snails.util.RequestUtil;
import com.kuzank.snails.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/14
 */
@RestController
@RequestMapping("/api/menu")
public class MenuApi {

    @Autowired
    MenuService menuService;
    @Autowired
    MenuJpa menuJpa;
    @Autowired
    PersonJpa personJpa;
    @Autowired
    SqlHelper sqlHelper;
    @Autowired
    PermissionJpa permissionJpa;
    @Autowired
    IdentityService identityService;
    @Autowired
    HttpServletRequest servletRequest;


    @GetMapping("/get")
    public Result get() {
        String token = RequestUtil.getToken(servletRequest);
        Person person = identityService.getPersonByToken(token);
        return Result.ofsuccess(person != null ? menuJpa.getByPerson(person.getId()) : new ArrayList<>());
    }

    @GetMapping("/getByPerson/{id}")
    public Result getByPerson(@PathVariable String id) {
        return Result.ofsuccess(menuJpa.getByPerson(id));
    }

    @GetMapping("/getByOrgunit/{id}")
    public Result getByOrgunit(@PathVariable String id) {
        return Result.ofsuccess(menuJpa.getByOrgunit(id));
    }


    @GetMapping("/configData")
    public Result configData() {

        Map map = new HashMap();

        String sql = String.format("" +
                "SELECT id,title,pid,'bank' as icon,false as permission FROM sys_orgunit\n" +
                "UNION ALL\n" +
                "SELECT id,title,orgunit AS pid,'user' as icon,false as permission FROM sys_person");

        map.put("menus", menuJpa.findAll());
        map.put("peoples", sqlHelper.query(sql));

        return Result.ofsuccess(map);
    }

    @GetMapping("/configDetail/{id}")
    public Result detail(@PathVariable String id) {

        Menu menu = new Menu();

        Optional<Menu> optional = menuJpa.findById(id);
        if (optional.isPresent()) {
            menu = optional.get();
        }

        Map map = new HashMap();
        map.put("menu", menu);
        map.put("permission", permissionJpa.getByResource(id));

        return Result.ofsuccess(map);
    }

    @PostMapping("/create")
    public Result create(@RequestBody Menu menu) {
        menu.setId(UuidUtil.randomUUID());
        menuService.initIdpathByParent(menu);
        menuJpa.save(menu);
        return Result.ofsuccess(menu);
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody Menu menu) {
        Optional<Menu> optional = menuJpa.findById(menu.getId());
        if (optional.isPresent()) {
            menuService.initIdpathByParent(menu);
            menuJpa.save(menu);
            return Result.ofsuccess(true);
        }
        return Result.ofsuccess(false);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        menuJpa.deleteByIdpathLike("%" + id + "%");
        return Result.ofsuccess(true);
    }

}
