package com.example.Eindproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
//    geeft de home pagina weer
    @GetMapping("/")
    public String index(){

        return "index";
    }
}
