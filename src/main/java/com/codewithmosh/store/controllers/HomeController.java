package com.codewithmosh.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

 // http://localhost:8080/

    // this is for returning the html pages to client
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "AR-J");
        return "index";
    }

}
