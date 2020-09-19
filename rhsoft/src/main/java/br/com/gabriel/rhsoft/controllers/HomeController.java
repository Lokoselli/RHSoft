package br.com.gabriel.rhsoft.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController{


    @RequestMapping(name = "index")
    public String index(){
        return "home";
    }

}