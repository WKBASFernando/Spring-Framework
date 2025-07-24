package com.ijse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ExactMappingController {
    @GetMapping
    public String exactMapping() {
        return "Hello";
    }

    @GetMapping("hello")
    public String exactMapping1() {
        return "Hello 2";
    }

    @GetMapping("hello/three")
    public String exactMapping2() {
        return "Hello 3";
    }

    @GetMapping("hello/four")
    public String exactMapping3() {
        return "Hello 4";
    }
}
