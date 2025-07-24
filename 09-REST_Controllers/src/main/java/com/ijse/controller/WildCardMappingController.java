package com.ijse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wild")
public class WildCardMappingController {
    @GetMapping("test/*/wild")
    public String wild() {
        return "WildCardMappingController";
    }

}
