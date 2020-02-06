package com.kuzank.snails.init;

import com.kuzank.snails.jpa.SelectItemJpa;
import com.kuzank.snails.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <p>Description: 初始化系统枚举</p>
 *
 * @author kuzank 2020/1/14
 */
@Order(3)
@Component
public class InitSelectitem implements Initialize {

    @Autowired
    SelectItemJpa selectItemJpa;


    @Override
    public void initialize() throws Exception {

        SelectItem root = SelectItem.ofRoot("system", "Snails 枚举", "a");

        SelectItem normal = SelectItem.ofParent("normal", "通用", root, "a");

        SelectItem normal_whether = SelectItem.ofParent(SelectItem.Whether.whether.getName(), "是否", normal, "a");
        SelectItem normal_whether_yes = SelectItem.ofParent(SelectItem.Whether.yes.getName(), "是", normal_whether, "a");
        SelectItem normal_whether_no = SelectItem.ofParent(SelectItem.Whether.no.getName(), "否", normal_whether, "b");


        selectItemJpa.saveAll(Arrays.asList(
                root, normal, normal_whether, normal_whether_yes, normal_whether_no
        ));
    }

}
