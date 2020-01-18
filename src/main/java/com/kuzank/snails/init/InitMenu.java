package com.kuzank.snails.init;

import com.kuzank.snails.jpa.MenuJpa;
import com.kuzank.snails.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <p>Description: 初始化系统菜单</p>
 *
 * @author kuzank 2020/1/14
 */
@Order(2)
@Component
public class InitMenu implements Initialize {

    @Autowired
    MenuJpa menuJpa;


    @Override
    public void initialize() throws Exception {

        Menu menuRoot = Menu.of("31", "Snails Web", null, null, null);
        Menu menuDev = Menu.of("32", "开发者平台", null, "rocket", menuRoot);

        Menu menuDev_dashboard = Menu.of("33", "首页", "/dashboard/v1", null, menuDev);
        Menu menuDev_user = Menu.of("34", "用户管理", "/person/list", null, menuDev);
        Menu menuDev_orgunit = Menu.of("35", "组织管理", "/system/orgunit", null, menuDev);
        Menu menuDev_menu = Menu.of("36", "菜单管理", "/system/menu", null, menuDev);
        Menu menuDev_menu_permission = Menu.of("37", "菜单权限", "/system/menu-permission", null, menuDev);
        Menu menuDev_online = Menu.of("38", "在线用户", "/person/online", null, menuDev);
        Menu menuDev_loginlog = Menu.of("39", "登陆日志", "/person/login-list", null, menuDev);
        Menu menuDev_httplog = Menu.of("40", "http请求", "/system/httplog/list", null, menuDev);
        Menu menuDev_exception = Menu.of("41", "系统异常", "/system/exceptio/list", null, menuDev);
        Menu menuDev_g2_custom = Menu.of("42", "G2图表", "/dashboard/v2", null, menuDev);

        menuJpa.saveAll(Arrays.asList(
                menuRoot, menuDev, menuDev_dashboard, menuDev_user, menuDev_orgunit, menuDev_menu, menuDev_menu_permission, menuDev_online, menuDev_loginlog, menuDev_httplog, menuDev_exception, menuDev_g2_custom
        ));

    }

}
