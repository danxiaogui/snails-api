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
        Menu menuRoot = Menu.of("31", "Snails Web", null, null, null).setUnInherit();
        Menu menuDev = Menu.of("32", "开发者平台", null, "rocket", menuRoot).setUnInherit();

        Menu menuDev_dashboard = Menu.of("33", "首页", "/dashboard/v1", null, menuDev).setInherit();
        Menu menuDev_user = Menu.of("34", "用户管理", "/person/list", null, menuDev).setUnInherit();
        Menu menuDev_orgunit = Menu.of("35", "组织管理", "/system/orgunit", null, menuDev).setUnInherit();
        Menu menuDev_menu = Menu.of("36", "菜单管理", "/system/menu", null, menuDev).setUnInherit();
        Menu menuDev_online = Menu.of("37", "在线用户", "/person/online", null, menuDev).setInherit();
        Menu menuDev_loginlog = Menu.of("38", "登陆日志", "/person/login-list", null, menuDev).setInherit();
        Menu menuDev_httplog = Menu.of("39", "http请求", "/system/httplog/list", null, menuDev).setInherit();
        Menu menuDev_exception = Menu.of("40", "系统异常", "/system/exceptio/list", null, menuDev).setInherit();
        Menu menuDev_g2_custom = Menu.of("41", "G2图表", "/dashboard/v2", null, menuDev).setInherit();

        menuJpa.saveAll(Arrays.asList(
                menuRoot, menuDev, menuDev_dashboard, menuDev_user, menuDev_orgunit, menuDev_menu, menuDev_online, menuDev_loginlog, menuDev_httplog, menuDev_exception, menuDev_g2_custom
        ));

        /**
         * 菜单权限配置
         */
        Permission menuRoot_OrgunitRoot_Permission = Permission.of("613f68e117384e008775b141a26c9cb0", menuRoot.getId(), orgunit_root_id);

        Permission menuDev_OrgunitDev_Permission = Permission.of("54439142310146529e8cd1497c4e67b9", menuDev.getId(), orgunit_dev_id);
        Permission menuDev_PersonDanxiaogui_Permission = Permission.of("f9e265a202b14369a88a1aac83f69e71", menuDev.getId(), person_danxiaogui_id);

        Permission menuDev_user_PersonKuzank_Permission = Permission.of("a517703947ab4741a89b3f769815a173", menuDev_user.getId(), person_kuzank_id);
        Permission menuDev_orgunit_PersonKuzank_Permission = Permission.of("9d9d01aad9af47b6a26e6a09332445a7", menuDev_orgunit.getId(), person_kuzank_id);
        Permission menuDev_menu_PersonKuzank_Permission = Permission.of("285c2b6ae8c6483d8b1ba68a6b4af462", menuDev_menu.getId(), person_kuzank_id);


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
