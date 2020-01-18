package com.kuzank.snails.jpa;

import com.kuzank.snails.model.Account;
import com.kuzank.snails.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.kuzank.snails.init.InitPerson.orgunit_dev_id;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/3
 */
@SpringBootTest
public class PersonJpaTest {

    @Autowired
    PersonJpa personJpa;
    @Autowired
    AccountJpa accountJpa;

    @Test
    public void findByUsernameAndPassword() {
        Person person = new Person();
        person.setId("1");
        person.setTitle("kuzank");

        Account account = new Account();
        account.setId("2");
        account.setPersonid(person.getId());
        account.setUsername("kuzank");
        account.setPassword("123456");

        personJpa.save(person);
        accountJpa.save(account);

        Optional<Person> optionalPerson1 = personJpa.findByUsernameAndPassword("kuzank", "123456");
        if (optionalPerson1.isPresent()) {
            System.out.println(optionalPerson1.get().toString());
            ;
        }

        Optional<Person> optionalPerson2 = personJpa.findByUsername("kuzank");
        if (optionalPerson2.isPresent()) {
            System.out.println(optionalPerson2.get().toString());
            ;
        }
    }

    @Test
    public void delete() {
        personJpa.deleteById("1");
    }

    @Test
    public void findAllByOrgunit() {
        List<Person> personList = personJpa.findAllByOrgunit(orgunit_dev_id);
        for (Person person : personList) {
            System.out.println(person);
        }
    }
}
