package com.kuzank.snails.api;

import com.kuzank.snails.core.Result;
import com.kuzank.snails.jpa.MenuJpa;
import com.kuzank.snails.jpa.PersonJpa;
import com.kuzank.snails.model.Menu;
import com.kuzank.snails.model.Person;
import com.kuzank.snails.service.MenuService;
import com.kuzank.snails.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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


    @GetMapping("/list")
    public Result list() {
        return Result.ofsuccess(menuJpa.findAll());
    }

    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable String id) {

        Menu menu = new Menu();
        List<Person> personList = personJpa.findAllByOrgunit(id);

        Optional<Menu> optional = menuJpa.findById(id);
        if (optional.isPresent()) {
            menu = optional.get();
        }

        Map map = new HashMap();
        map.put("menu", menu);
        map.put("personList", personList);

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
