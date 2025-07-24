package com.ijse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("param")
public class ParamController {
    @GetMapping(params = {"id"})
    public String getParam(@RequestParam("id") String id) {
        return id + "";
    }
}
