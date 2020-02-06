package com.kuzank.snails.api;

import com.kuzank.snails.core.Result;
import com.kuzank.snails.jpa.SelectItemJpa;
import com.kuzank.snails.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/10
 */
@RestController
@RequestMapping("/api/selectitem")
public class SelectitemApi {

    @Autowired
    SelectItemJpa selectItemJpa;

    @GetMapping("/findAll")
    public Result find() {
        return Result.ofsuccess(selectItemJpa.findAllByOrderBySorttAsc());
    }

    @GetMapping("/findByPid/{pid}")
    public Result findByPid(@PathVariable String pid,
                            @RequestParam(value = "enable", required = false) boolean enable) {

        List<SelectItem> list = selectItemJpa.findAllByPidOrderBySorttAsc(pid);
        if (enable) {
            list = list.stream().filter(item -> SelectItem.Whether.IsTrue(item.getEnable())).collect(Collectors.toList());
        }
        return Result.ofsuccess(list);
    }

    @PostMapping("/saveAll/{pid}")
    public Result saveAll(@PathVariable String pid, @RequestBody List<SelectItem> list) {
        selectItemJpa.deleteByPid(pid);
        selectItemJpa.saveAll(list);
        return Result.ofsuccess(true);
    }

    @PostMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        selectItemJpa.deleteByIdpathLike("%" + id + "%");
        return Result.ofsuccess(true);
    }
}
