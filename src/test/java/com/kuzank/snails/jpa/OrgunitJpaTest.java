package com.kuzank.snails.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.kuzank.snails.init.InitPerson.orgunit_root_id;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/14
 */
@SpringBootTest
public class OrgunitJpaTest {

    @Autowired
    OrgunitJpa orgunitJpa;

    @Test
    public void deleteByIdpathLike() {
        orgunitJpa.deleteByIdpathLike("%" + orgunit_root_id + "%");
    }

}
