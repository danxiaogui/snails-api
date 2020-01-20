package com.kuzank.snails.api;

import com.kuzank.snails.core.Result;
import com.kuzank.snails.jpa.HttpLogJpa;
import com.kuzank.snails.service.HttpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/10
 */
@RestController
@RequestMapping("/api/httpLog")
public class HttpLogApi {

    @Autowired
    HttpLogJpa httpLogJpa;
    @Autowired
    HttpLogService httpLogService;


    @GetMapping("num")
    public Result num() {
        return Result.ofsuccess(httpLogJpa.count());
    }

    @PostMapping("/find")
    public Result find(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                       @RequestParam(value = "pageSize", required = false) Integer pageSize,
                       @RequestBody LinkedHashMap queryParam) {
        return Result.ofsuccess(httpLogService.search(queryParam, pageNumber, pageSize));
    }

}
