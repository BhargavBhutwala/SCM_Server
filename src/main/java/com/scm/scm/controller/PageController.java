package com.scm.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) {
        //sending data to the view
        model.addAttribute("Name", "Bharggav");
        model.addAttribute("githubRepo", "https://github.com/BhargavBhutwala/SCM_Server");
        return "home";
    }
}
