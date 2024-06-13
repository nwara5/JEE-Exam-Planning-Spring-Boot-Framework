package com.Academia.GestionExamen.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/api/general")
public class GeneralController {

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/")
    public String home(){
        return "index";
    }
}
