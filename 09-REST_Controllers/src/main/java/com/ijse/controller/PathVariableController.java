package com.ijse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("path")
public class PathVariableController {
    @GetMapping(path = "{id}/{name}")
    public String hello(@PathVariable("id") String id, @PathVariable("name") String name) {
        return "ID: " + id + ", Name: " + name;
    }

    @GetMapping(path = "customer/{id:[I][0-9]{3}}")
    public String helloCustomer(@PathVariable("id") String id) {
        return "ID: " + id;
    }

    @GetMapping(path = "item/{code}/branch/{city}")
    public String helloBranch(@PathVariable("code") String code, @PathVariable("city") String city) {
        return "Code: " + code + ", City: " + city;
    }
}
