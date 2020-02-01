package com.kuzank.snails.helper;

import com.kuzank.snails.util.GsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/31
 */
@SpringBootTest
public class SqlHelperTest {

    @Autowired
    SqlHelper sqlHelper;

    @Test
    public void query1() {

        String sql = "select * from sys_person";
        List list = sqlHelper.query(sql);

        for (Object o : list) {
            System.out.println(GsonUtil.toJson(o));
        }
    }

    @Test
    public void query2() {

        String sql = "select * from sys_person where title = :title";
        Map map = new HashMap();
        map.put("title", "kuzank");

        List list = sqlHelper.query(sql, map);

        for (Object o : list) {
            System.out.println(GsonUtil.toJson(o));
        }
    }

    @Test
    public void execute1() {

        String sql = "update sys_person set description = '1'";
        sqlHelper.execute(sql);

        sql = "select * from sys_person";
        List list = sqlHelper.query(sql);

        for (Object o : list) {
            System.out.println(GsonUtil.toJson(o));
        }
    }


    @Test
    public void execute2() {

        String sql = "update sys_person set description = :description";
        Map map = new HashMap();
        map.put("description", "description...");

        sqlHelper.execute(sql, map);

        sql = "select * from sys_person";
        List list = sqlHelper.query(sql);

        for (Object o : list) {
            System.out.println(GsonUtil.toJson(o));
        }
    }
}
