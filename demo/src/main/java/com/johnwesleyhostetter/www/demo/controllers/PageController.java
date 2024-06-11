package com.johnwesleyhostetter.www.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {
    @RequestMapping(value = "/")
    public String index() {
        return "Hello World";
    }
}
