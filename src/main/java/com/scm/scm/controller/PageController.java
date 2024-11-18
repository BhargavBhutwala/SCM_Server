package com.scm.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.entities.User;
import com.scm.scm.forms.UserForm;
import com.scm.scm.services.UserService;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model) {
        // sending data to the view
        model.addAttribute("Name", "Bharggav");
        model.addAttribute("githubRepo", "https://github.com/BhargavBhutwala/SCM_Server");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", "true");
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage() {
        return "services";
    }

    @RequestMapping("/contacts")
    public String contactsPage() {
        return "contact";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/signup")
    public String signupPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    // processing registration
    @PostMapping("/do-register")
    public String processRegistration(@ModelAttribute UserForm userForm) {
        // fetch form data
        // UserForm
        System.out.println(userForm);

        // validate form data [TODO]

        // save user data to database [user service]
        // userForm --> user
        User user = User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .phoneNumber(userForm.getPhoneNumber())
                .about(userForm.getAbout())
                .profilePic(
                        "https://www.shutterstock.com/image-vector/default-avatar-profile-icon-social-media-1677509740")
                .build();
        userService.saveUser(user);

        // message "Registration successful"
        System.out.println("user saved successfully");

        // redirect to login page
        return "redirect:/login";
    }
}
