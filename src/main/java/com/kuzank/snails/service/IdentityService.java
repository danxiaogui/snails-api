package com.kuzank.snails.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kuzank.snails.jpa.PersonJpa;
import com.kuzank.snails.model.Person;
import com.kuzank.snails.util.GsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/3
 */
@Service
public class IdentityService {

    private Cache<String, Map<String, Object>> cache;

    @Autowired
    PersonJpa personJpa;
    @Autowired
    HttpServletRequest servletRequest;


    public IdentityService() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(10000).expireAfterAccess(10, TimeUnit.HOURS)
                .build();
    }

    /**
     * 身份校验，判断用户是否可以登陆系统
     *
     * @param loginIdentity
     * @return
     */
    public Person identityCheck(Map loginIdentity) {

        return getPersonByIdentity(loginIdentity);
    }

    public boolean isLogin(String token) {

        return token != null && cache.getIfPresent(token) != null;
    }

    public void login(String token, Map loginIdentity, Person person) {
        if (token != null && loginIdentity != null && person != null) {

            Map personMap = GsonUtil.objectToMap(person);
            loginIdentity.putAll(personMap);
            loginIdentity.put("loginTime", LocalDateTime.now().toString());
            loginIdentity.put("userAgent", servletRequest.getHeader("User-Agent"));

            cache.put(token, loginIdentity);
        }
    }

    public void logout(String token) {
        if (token != null) {
            cache.invalidate(token);
        }
    }

    public Map getByToken(String token) {

        Map loginIdentity = cache.getIfPresent(token);

        return loginIdentity;
    }

    public Person getPersonByToken(String token) {

        Map loginIdentity = cache.getIfPresent(token);

        return getPersonByIdentity(loginIdentity);
    }

    public Person getPersonByIdentity(Map loginIdentity) {

        if (loginIdentity != null) {
            String username = null == loginIdentity.get("username") ? null : loginIdentity.get("username").toString();
            String password = null == loginIdentity.get("password") ? null : loginIdentity.get("password").toString();

            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                Optional<Person> optionalPerson = personJpa.findByUsernameAndPassword(username, password);
                if (optionalPerson.isPresent()) {
                    return optionalPerson.get();
                }
            }
        }
        return null;
    }

    public List<Map> getAllPeople() {
        List<Map> maps = new ArrayList<>();

        if (cache != null && cache.size() > 0) {
            cache.asMap().forEach((key, value) -> {
                maps.add(value);
            });
        }

        return maps;
    }
}
