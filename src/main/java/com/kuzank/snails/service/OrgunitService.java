package com.kuzank.snails.service;

import com.kuzank.snails.jpa.OrgunitJpa;
import com.kuzank.snails.model.Orgunit;
import com.kuzank.snails.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/14
 */
@Service
public class OrgunitService {

    @Autowired
    OrgunitJpa orgunitJpa;


    public void initIdpathByParent(Orgunit orgunit) {

        if (StringUtil.isNotBlank(orgunit.getPid())) {
            Optional<Orgunit> optional = orgunitJpa.findById(orgunit.getPid());
            if (optional.isPresent()) {
                orgunit.setIdpath(optional.get().getIdpath() + "," + orgunit.getId());
            } else {
                orgunit.setIdpath(orgunit.getId());
            }
        } else {
            orgunit.setIdpath(orgunit.getId());
        }
    }

}
