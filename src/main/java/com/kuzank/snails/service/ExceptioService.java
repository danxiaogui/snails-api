package com.kuzank.snails.service;

import com.kuzank.snails.jpa.ExceptioJpa;
import com.kuzank.snails.model.Exceptio;
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
 * @author kuzank 2020/1/13
 */
@Service
public class ExceptioService implements IService<Exceptio> {

    @Autowired
    ExceptioJpa exceptioJpa;

    @Override
    public Page search(LinkedHashMap queryParam, int pageNumber, int pageSize) {

        Pageable pageable = ofPageable(queryParam, pageNumber, pageSize);

        Page page = exceptioJpa.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();

            if (queryParam != null) {

                if (StringUtil.isNotBlank(queryParam.get("personid"))) {
                    predicatesList.add(criteriaBuilder.and(criteriaBuilder.like(root.get("personid"), "%" + queryParam.get("personid") + "%")));
                }

                if (StringUtil.isNotBlank(queryParam.get("url"))) {
                    predicatesList.add(criteriaBuilder.and(criteriaBuilder.like(root.get("url"), "%" + queryParam.get("url") + "%")));
                }

                if (StringUtil.isNotBlank(queryParam.get("method"))) {
                    predicatesList.add(criteriaBuilder.and(criteriaBuilder.like(root.get("method"), "%" + queryParam.get("method") + "%")));
                }

                // 大于或等于传入时间
                if (StringUtil.isNotBlank(queryParam.get("start"))) {
                    predicatesList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createtime").as(String.class), queryParam.get("start").toString()));
                }

                // 小于或等于传入时间
                if (StringUtil.isNotBlank(queryParam.get("end"))) {
                    predicatesList.add(criteriaBuilder.lessThanOrEqualTo(root.get("createtime").as(String.class), queryParam.get("end").toString()));
                }
            }

            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        }, pageable);

        return page;
    }
}
