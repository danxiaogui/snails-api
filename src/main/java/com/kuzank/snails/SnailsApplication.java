package com.kuzank.snails;

import com.kuzank.snails.core.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SnailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnailsApplication.class, args);
    }

    @GetMapping("/test")
    public Result test() {
        return Result.ofsuccess(null);
    }
}
