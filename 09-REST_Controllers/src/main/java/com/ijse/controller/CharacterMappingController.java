package com.ijse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("char")
public class CharacterMappingController {
    @GetMapping("item/I???")
    public String sayHello() {
        return "say hello";
    }

    @GetMapping("????/search")
    public String search() {
        return "search";
    }
}
