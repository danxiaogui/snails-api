package com.kuzank.snails.config;

import com.kuzank.snails.core.Result;
import com.kuzank.snails.jpa.ExceptioJpa;
import com.kuzank.snails.model.Exceptio;
import com.kuzank.snails.service.IdentityService;
import com.kuzank.snails.util.RequestUtil;
import com.kuzank.snails.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/13
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    IdentityService identityService;
    @Autowired
    ExceptioJpa exceptioJpa;


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {

        String personid = null;
        String token = RequestUtil.getToken(request);
        if (StringUtil.isNotBlank(token)) {
            if (identityService.getByToken(token) != null) {
                Map identityMap = identityService.getByToken(token);
                personid = String.valueOf(identityMap.get("id"));
            }
        }

        Exceptio e = Exceptio.of(personid, request);
        e.setDescription(getStackTrace(exception));

        exceptioJpa.save(e);

        return Result.oflost(1);
    }

    private String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
