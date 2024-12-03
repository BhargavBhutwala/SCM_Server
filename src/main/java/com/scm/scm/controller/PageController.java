package com.scm.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.entities.User;
import com.scm.scm.forms.UserForm;
import com.scm.scm.helper.Message;
import com.scm.scm.helper.MessageType;
import com.scm.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

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
    public String processRegistration(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult,
            HttpSession session) {
        // fetch form data
        // UserForm
        System.out.println(userForm);

        // validate form data
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // save user data to database [user service]
        // userForm --> user

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setAbout(userForm.getAbout());
        user.setProfilePic(
                "https://www.shutterstock.com/image-vector/default-avatar-profile-icon-social-media-1677509740");

        // save user to database
        userService.saveUser(user);

        // message "Registration successful"
        System.out.println("user saved successfully");

        Message message = new Message();
        message.setContent("Registration successful!");
        message.setType(MessageType.green);

        session.setAttribute("alert", message);

        // redirect to login page
        return "redirect:/signup";
    }
}
