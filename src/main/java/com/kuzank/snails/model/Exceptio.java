package com.kuzank.snails.model;

import com.kuzank.snails.util.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_exception")
public class Exceptio extends Base {

    private String personid;
    private String url;
    private String method;
    private String ipaddress;
    private String param;
    private String origin;
    private String agent;
    private String language;


    public static Exceptio of(String personid, HttpServletRequest request) {

        String url = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");

        Exceptio obj = new Exceptio();
        obj.setPersonid(personid);
        obj.setUrl(url);
        obj.setMethod(request.getMethod());
        obj.setIpaddress(request.getRemoteAddr());
        obj.setOrigin(request.getHeader("Origin"));
        obj.setAgent(request.getHeader("User-Agent"));
        obj.setLanguage(request.getHeader("Accept-Language"));

        Map parameterMap = request.getParameterMap();
        if (parameterMap != null) {
            obj.setParam(GsonUtil.fromJson(parameterMap));
        }

        return obj;
    }
}
