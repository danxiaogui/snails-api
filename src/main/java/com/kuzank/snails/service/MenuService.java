package com.kuzank.snails.service;

import com.kuzank.snails.jpa.MenuJpa;
import com.kuzank.snails.model.Menu;
import com.kuzank.snails.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/14
 */
@Service
public class MenuService {

    @Autowired
    MenuJpa menuJpa;


    public void initIdpathByParent(Menu menu) {

        if (StringUtil.isNotBlank(menu.getPid())) {
            Optional<Menu> optional = menuJpa.findById(menu.getPid());
            if (optional.isPresent()) {
                menu.setIdpath(optional.get().getIdpath() + "," + menu.getId());
            } else {
                menu.setIdpath(menu.getId());
            }
        } else {
            menu.setIdpath(menu.getId());
        }
    }

}
