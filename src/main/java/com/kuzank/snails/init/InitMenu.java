package com.kuzank.snails.init;

import com.kuzank.snails.jpa.MenuJpa;
import com.kuzank.snails.jpa.PermissionJpa;
import com.kuzank.snails.model.Menu;
import com.kuzank.snails.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.kuzank.snails.init.InitPerson.*;

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
    @Autowired
    PermissionJpa permissionJpa;


    @Override
    public void initialize() throws Exception {

        /**
         * 菜单配置
         */
        Menu menuRoot = Menu.of("m0000000000000000000000000000001", "Snails Web", null, null, null).setUnInherit();
        Menu menuDev = Menu.of("m0000000000000000000000000000002", "开发者平台", null, "rocket", menuRoot).setUnInherit();

        Menu menuDev_dashboard = Menu.of("m0000000000000000000000000000003", "首页", "/dashboard/v1", null, menuDev).setInherit();
        Menu menuDev_user = Menu.of("m0000000000000000000000000000004", "用户管理", "/person/list", null, menuDev).setUnInherit();
        Menu menuDev_orgunit = Menu.of("m0000000000000000000000000000005", "组织管理", "/system/orgunit", null, menuDev).setUnInherit();
        Menu menuDev_menu = Menu.of("m0000000000000000000000000000006", "菜单管理", "/system/menu", null, menuDev).setUnInherit();
        Menu menuDev_online = Menu.of("m0000000000000000000000000000007", "在线用户", "/person/online", null, menuDev).setInherit();
        Menu menuDev_loginlog = Menu.of("m0000000000000000000000000000008", "登陆日志", "/person/login-list", null, menuDev).setInherit();
        Menu menuDev_httplog = Menu.of("m0000000000000000000000000000009", "http请求", "/system/httplog/list", null, menuDev).setInherit();
        Menu menuDev_exception = Menu.of("m0000000000000000000000000000010", "系统异常", "/system/exceptio/list", null, menuDev).setInherit();
        Menu menuDev_g2_custom = Menu.of("m0000000000000000000000000000011", "G2图表", "/dashboard/v2", null, menuDev).setInherit();

        menuJpa.saveAll(Arrays.asList(
                menuRoot, menuDev, menuDev_dashboard, menuDev_user, menuDev_orgunit, menuDev_menu, menuDev_online, menuDev_loginlog, menuDev_httplog, menuDev_exception, menuDev_g2_custom
        ));

        /**
         * 菜单权限配置
         */
        Permission menuRoot_OrgunitRoot_Permission = Permission.of("p0000000000000000000000000000001", menuRoot.getId(), orgunit_root_id);

        Permission menuDev_OrgunitDev_Permission = Permission.of("p0000000000000000000000000000002", menuDev.getId(), orgunit_dev_id);
        Permission menuDev_PersonDanxiaogui_Permission = Permission.of("p0000000000000000000000000000003", menuDev.getId(), person_danxiaogui_id);

        Permission menuDev_user_PersonKuzank_Permission = Permission.of("p0000000000000000000000000000004", menuDev_user.getId(), person_kuzank_id);
        Permission menuDev_orgunit_PersonKuzank_Permission = Permission.of("p0000000000000000000000000000005", menuDev_orgunit.getId(), person_kuzank_id);
        Permission menuDev_menu_PersonKuzank_Permission = Permission.of("p0000000000000000000000000000006", menuDev_menu.getId(), person_kuzank_id);


        permissionJpa.saveAll(Arrays.asList(
                menuRoot_OrgunitRoot_Permission
                , menuDev_OrgunitDev_Permission
                , menuDev_PersonDanxiaogui_Permission
                , menuDev_user_PersonKuzank_Permission
                , menuDev_orgunit_PersonKuzank_Permission
                , menuDev_menu_PersonKuzank_Permission

        ));

    }

}
