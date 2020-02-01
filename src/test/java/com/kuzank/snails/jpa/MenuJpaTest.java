package com.kuzank.snails.jpa;

import com.kuzank.snails.model.Menu;
import com.kuzank.snails.util.GsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.kuzank.snails.init.InitPerson.orgunit_dev_id;
import static com.kuzank.snails.init.InitPerson.person_kuzank_id;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/3
 */
@SpringBootTest
public class MenuJpaTest {

    @Autowired
    MenuJpa menuJpa;

    @Test
    public void getByPerson() {
        List<Menu> menus = menuJpa.getByPerson(person_kuzank_id);
        menus.forEach(item -> System.out.println(GsonUtil.toJson(item)));
    }

    @Test
    public void getByOrgunit() {
        List<Menu> menus = menuJpa.getByOrgunit(orgunit_dev_id);
        menus.forEach(item -> System.out.println(GsonUtil.toJson(item)));
    }
}
