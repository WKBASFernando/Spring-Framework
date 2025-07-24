package com.ijse.controller;

import com.ijse.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("form")
public class FormURLDataController {
    @PostMapping("save")
    public String saveUser(@ModelAttribute UserDto userDto) {
        System.out.println(userDto);
        return "saveUser";
    }
}
