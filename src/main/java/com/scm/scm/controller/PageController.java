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

    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("isLogin", "true");
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage(){
        return "services";
    }

    @RequestMapping("/contacts")
    public String contactsPage(){
        return "contact";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/signup")
    public String signupPage(){
        return "register";
    }
}
