package com.kuzank.snails.api;

import com.kuzank.snails.core.Result;
import com.kuzank.snails.jpa.OrgunitJpa;
import com.kuzank.snails.jpa.PersonJpa;
import com.kuzank.snails.model.Orgunit;
import com.kuzank.snails.model.Person;
import com.kuzank.snails.service.OrgunitService;
import com.kuzank.snails.util.StringUtil;
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
@RequestMapping("/api/orgunit")
public class OrgunitApi {

    @Autowired
    OrgunitService orgunitService;
    @Autowired
    OrgunitJpa orgunitJpa;
    @Autowired
    PersonJpa personJpa;


    @GetMapping("/list")
    public Result list() {
        return Result.ofsuccess(orgunitJpa.findAll());
    }

    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable String id) {

        Orgunit orgunit = new Orgunit();
        List<Person> personList = personJpa.findAllByOrgunit(id);

        Optional<Orgunit> optional = orgunitJpa.findById(id);
        if (optional.isPresent()) {
            orgunit = optional.get();
        }

        Map map = new HashMap();
        map.put("orgunit", orgunit);
        map.put("personList", personList);

        return Result.ofsuccess(map);
    }

    @PostMapping("/create")
    public Result create(@RequestParam(required = false) String ids, @RequestBody Orgunit orgunit) {
        orgunit.setId(UuidUtil.randomUUID());
        orgunitService.initIdpathByParent(orgunit);
        orgunitJpa.save(orgunit);

        if (StringUtil.isNotBlank(ids)) {
            String[] idarr = ids.split(",");
            for (String id : idarr) {
                Optional<Person> optionalPerson = personJpa.findById(id);
                if (optionalPerson.isPresent()) {
                    Person person = optionalPerson.get();
                    person.setOrgunit(orgunit.getId());
                    personJpa.save(person);
                }
            }
        }
        return Result.ofsuccess(orgunit);
    }

    @PostMapping("/edit")
    public Result edit(@RequestParam(required = false) String ids, @RequestBody Orgunit orgunit) {
        Optional<Orgunit> _orgunit = orgunitJpa.findById(orgunit.getId());
        if (_orgunit.isPresent()) {
            orgunitService.initIdpathByParent(orgunit);
            orgunitJpa.save(orgunit);

            List<Person> personList = personJpa.findAllByOrgunit(orgunit.getId());
            personList.forEach(item -> {
                item.setOrgunit(null);
                personJpa.save(item);
            });

            if (StringUtil.isNotBlank(ids)) {
                String[] idarr = ids.split(",");
                for (String id : idarr) {
                    Optional<Person> optionalPerson = personJpa.findById(id);
                    if (optionalPerson.isPresent()) {
                        Person person = optionalPerson.get();
                        person.setOrgunit(orgunit.getId());
                        personJpa.save(person);
                    }
                }
            }

            return Result.ofsuccess(true);
        }
        return Result.ofsuccess(false);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        orgunitJpa.deleteByIdpathLike("%" + id + "%");
        return Result.ofsuccess(true);
    }

}
