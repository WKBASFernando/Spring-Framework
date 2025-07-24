package com.ijse.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
public class HelloController {
    @GetMapping
    public String getMapping() {
        return "GET";
    }

    @GetMapping(path = "/get2")
    public String getMapping2() {
        return "GET2";
    }

    @PostMapping
    public String postMapping() {
        return "POST";
    }

    @PutMapping
    public String putMapping() {
        return "PUT";
    }

    @DeleteMapping
    public String deleteMapping() {
        return "DELETE";
    }

    @PatchMapping
    public String patchMapping() {
        return "PATCH";
    }
}
