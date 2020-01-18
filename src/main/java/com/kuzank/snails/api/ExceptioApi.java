package com.kuzank.snails.api;

import com.kuzank.snails.core.Result;
import com.kuzank.snails.service.ExceptioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/13
 */
@RestController
@RequestMapping("/api/exceptio")
public class ExceptioApi {

    @Autowired
    ExceptioService exceptioService;

    @PostMapping("/find")
    public Result find(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                       @RequestParam(value = "pageSize", required = false) Integer pageSize,
                       @RequestBody LinkedHashMap queryParam) {
        return Result.ofsuccess(exceptioService.search(queryParam, pageNumber, pageSize));
    }

}
