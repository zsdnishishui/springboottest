package com.zsd.springboottest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zsd.service.ServiceHello;

@RestController
@RequestMapping("/base")
public class HelloController {

    @Autowired
    private ServiceHello serviceHello;

    @GetMapping("/")
    public String hello() {

         return serviceHello.hello();
    }
}
