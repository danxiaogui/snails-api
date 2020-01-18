package com.kuzank.snails.service;

import com.kuzank.snails.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.LinkedHashMap;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/3
 */
@SpringBootTest
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Test
    public void search() {
        int pageNumber = 0;
        int pageSize = 20;

        LinkedHashMap queryMap = new LinkedHashMap();
        queryMap.put("title", "ku");

        Page<Person> page = personService.search(queryMap, pageNumber, pageSize);

        System.out.println(page);
        System.out.println(page.getTotalPages());
        page.getContent().forEach(p -> System.out.println(p));
    }

}
