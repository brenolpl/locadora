package com.brenoleal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teste")
public class TestController {

    @GetMapping("1")
    public String teste() {
        return "1";
    }
}
