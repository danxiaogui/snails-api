package com.kuzank.snails.service;

import com.kuzank.snails.jpa.PersonJpa;
import com.kuzank.snails.model.Person;
import com.kuzank.snails.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/3
 */
@Service
public class PersonService implements IService<Person> {

    @Autowired
    PersonJpa personJpa;

    @Override
    public Page<Person> search(LinkedHashMap queryParam, int pageNumber, int pageSize) {

        Pageable pageable = ofPageable(queryParam, pageNumber, pageSize);

        Page<Person> page = personJpa.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();

            if (queryParam != null) {

                if (StringUtil.isNotBlank(queryParam.get("title"))) {
                    predicatesList.add(criteriaBuilder.and(criteriaBuilder.like(root.get("title"), "%" + queryParam.get("title") + "%")));
                }

                if (StringUtil.isNotBlank(queryParam.get("phone"))) {
                    predicatesList.add(criteriaBuilder.and(criteriaBuilder.like(root.get("phone"), "%" + queryParam.get("phone") + "%")));
                }

                if (StringUtil.isNotBlank(queryParam.get("address"))) {
                    predicatesList.add(criteriaBuilder.and(criteriaBuilder.like(root.get("address"), "%" + queryParam.get("address") + "%")));
                }

                if (StringUtil.isNotBlank(queryParam.get("gender"))) {
                    predicatesList.add(criteriaBuilder.equal(root.get("gender").as(String.class), queryParam.get("gender")));
                }
            }

            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        }, pageable);

        return page;
    }

}
