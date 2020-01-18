package com.kuzank.snails.init;

import com.kuzank.snails.jpa.AccountJpa;
import com.kuzank.snails.jpa.OrgunitJpa;
import com.kuzank.snails.jpa.PersonJpa;
import com.kuzank.snails.model.Account;
import com.kuzank.snails.model.Orgunit;
import com.kuzank.snails.model.Person;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <p>Description: 初始化账号、人员、组织信息</p>
 *
 * @author kuzank 2020/1/14
 */
@Order(1)
@Component
public class InitPerson implements Initialize {

    @Autowired
    AccountJpa accountJpa;
    @Autowired
    PersonJpa personJpa;
    @Autowired
    OrgunitJpa orgunitJpa;

    public static String person_kuzank_id = "01";
    public static String person_danxiaogui_id = "02";

    public static String account_kuzan_id = "11";
    public static String account_danxiaogui_id = "12";

    public static String orgunit_root_id = "21";
    public static String orgunit_dev_id = "22";
    public static String orgunit_finance_id = "23";


    @Override
    public void initialize() throws Exception {

        Person person_kuzank = Person.of(person_kuzank_id, "kuzank", orgunit_dev_id);
        Person person_danxiaogui = Person.of(person_danxiaogui_id, "danxiaogui", orgunit_finance_id);

        Account account_kuzan = Account.of(account_kuzan_id, "kuzan", DigestUtils.md5Hex("123456"), person_kuzank_id);
        Account account_danxiaogui = Account.of(account_danxiaogui_id, "danxiaogui", DigestUtils.md5Hex("123456"), person_danxiaogui_id);

        Orgunit orgunit_root = Orgunit.of(orgunit_root_id, "Snails Studio", null, null);
        Orgunit orgunit_dev = Orgunit.of(orgunit_dev_id, "技术部", person_kuzank_id, orgunit_root);
        Orgunit orgunit_finance = Orgunit.of(orgunit_finance_id, "财务部", person_danxiaogui_id, orgunit_root);


        personJpa.saveAll(
                Arrays.asList(person_kuzank, person_danxiaogui)
        );
        accountJpa.saveAll(
                Arrays.asList(account_kuzan, account_danxiaogui)
        );
        orgunitJpa.saveAll(
                Arrays.asList(orgunit_root, orgunit_dev, orgunit_finance)
        );
    }

}
