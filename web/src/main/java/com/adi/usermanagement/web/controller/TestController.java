package com.adi.usermanagement.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('USER_READ')")
    public String hello() {
        return "Hello";
    }
}
