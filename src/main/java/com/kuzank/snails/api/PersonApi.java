package com.kuzank.snails.api;

import com.kuzank.snails.core.Audience;
import com.kuzank.snails.core.Result;
import com.kuzank.snails.jpa.LoginJpa;
import com.kuzank.snails.jpa.PersonJpa;
import com.kuzank.snails.model.Login;
import com.kuzank.snails.model.Person;
import com.kuzank.snails.service.IdentityService;
import com.kuzank.snails.service.LoginService;
import com.kuzank.snails.service.PersonService;
import com.kuzank.snails.util.CreateTokenUtil;
import com.kuzank.snails.util.GsonUtil;
import com.kuzank.snails.util.RequestUtil;
import com.kuzank.snails.util.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/2
 */
@RestController
@RequestMapping("/api/person")
public class PersonApi {

    @Autowired
    Audience audience;
    @Autowired
    PersonJpa personJpa;
    @Autowired
    LoginJpa loginJpa;
    @Autowired
    LoginService loginService;
    @Autowired
    PersonService personService;
    @Autowired
    IdentityService identityService;
    @Autowired
    HttpServletRequest servletRequest;


    @GetMapping("num")
    public Result num() {
        return Result.ofsuccess(personJpa.count());
    }

    @PostMapping("/account/login")
    public Result create(@RequestBody Map loginIdentity) {

        if (loginIdentity == null) {
            return Result.oflost("登陆信息为空！", 201);
        }

        Person person = identityService.identityCheck(loginIdentity);
        if (person != null) {
            //  生成token
            String accessToken = CreateTokenUtil.createJWT(person.getTitle(), audience.getClientId(), audience.getName(), audience.getExpiresSecond() * 1000, audience.getBase64Secret());
            // 将 token 保存在内存中，以后请求 API 时，从 request header 取出 token 进行判读【用户是否已登陆】
            identityService.login(accessToken, loginIdentity, person);

            // 添加登陆日志
            loginJpa.save(Login.of(person.getId(), accessToken, servletRequest));

            Map<String, Object> map = GsonUtil.objectToMap(person);
            map.put("token", accessToken);

            return Result.ofsuccess(map);
        }

        String username = null == loginIdentity.get("username") ? null : loginIdentity.get("username").toString();
        String password = null == loginIdentity.get("password") ? null : loginIdentity.get("password").toString();
        if (StringUtils.isEmpty(username)) {
            return Result.oflost("账号信息为空！", 201);
        }
        if (StringUtils.isEmpty(password)) {
            return Result.oflost("密码信息为空！", 201);
        }
        Optional<Person> _option = personJpa.findByUsername(username);
        if (!_option.isPresent()) {
            return Result.oflost("账号不存在！", 201);
        } else {
            return Result.oflost("密码错误！", 201);
        }
    }

    @GetMapping("/isLogin")
    public Result isLogin() {
        String token = RequestUtil.getToken(servletRequest);
        if (identityService.isLogin(token)) {
            return Result.ofsuccess(true);
        }
        return Result.oflost(null, 401);
    }

    @GetMapping("/logout")
    public Result logout() {
        String token = RequestUtil.getToken(servletRequest);
        if (StringUtils.isNotEmpty(token)) {

            identityService.logout(token);

            Optional<Login> optionalLogin = loginJpa.findByToken(token);
            if (optionalLogin.isPresent()) {
                Login login = optionalLogin.get();
                login.setLogouttime(LocalDateTime.now().toString());
                loginJpa.save(login);
            }
        }
        return Result.ofsuccess(null);
    }

    @PostMapping("/find")
    public Result find(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                       @RequestParam(value = "pageSize", required = false) Integer pageSize,
                       @RequestBody LinkedHashMap queryParam) {
        return Result.ofsuccess(personService.search(queryParam, pageNumber, pageSize));
    }

    @PostMapping("/log")
    public Result Loginfind(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                            @RequestParam(value = "pageSize", required = false) Integer pageSize,
                            @RequestBody LinkedHashMap queryParam) {
        return Result.ofsuccess(loginService.search(queryParam, pageNumber, pageSize));
    }

    @PostMapping("/online")
    public Result findLogin() {
        return Result.ofsuccess(identityService.getAllPeople());
    }

    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable String id) {
        Optional<Person> optionalPerson = personJpa.findById(id);
        if (optionalPerson.isPresent()) {
            return Result.ofsuccess(optionalPerson.get());
        }
        return Result.oflost("", 201);
    }

    @PostMapping("/create")
    public Result create(@RequestBody Person person) {
        person.setId(UuidUtil.randomUUID());
        return Result.ofsuccess(personJpa.save(person));
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody Person person) {
        Optional<Person> _person = personJpa.findById(person.getId());
        if (_person.isPresent()) {
            personJpa.save(person);
            return Result.ofsuccess(true);
        }
        return Result.ofsuccess(false);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        personJpa.deleteById(id);
        return Result.ofsuccess(true);
    }
}
