package com.kuzank.snails.service;

import com.kuzank.snails.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/10
 */
public interface IService<T> {


    Page<T> search(LinkedHashMap<String, Object> queryParam, int pageNumber, int pageSize);


    default Pageable ofPageable(LinkedHashMap<String, Object> queryParam, int pageNumber, int pageSize) {

        List<Sort.Order> orderList = new ArrayList<>();
        if (queryParam != null) {
            for (String key : queryParam.keySet()) {
                Object value = queryParam.get(key);
                if (key.endsWith("_sort") && StringUtil.isNotBlank(value)) {
                    ofSort(orderList, key.replace("_sort", ""), value);
                }
            }
        }
        Pageable pageable = orderList.size() == 0 ?
                PageRequest.of(pageNumber, pageSize) :
                PageRequest.of(pageNumber, pageSize, Sort.by(orderList));

        return pageable;
    }

    default void ofSort(List<Sort.Order> orderList, String sortkey, Object sortvalue) {
        if ("ascend".equals(sortvalue)) {
            orderList.add(new Sort.Order(Sort.Direction.ASC, sortkey));
        }
        if ("descend".equals(sortvalue)) {
            orderList.add(new Sort.Order(Sort.Direction.DESC, sortkey));
        }
    }
}
