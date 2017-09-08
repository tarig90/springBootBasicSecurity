package com.spring_boot2017127.demo.ControllerLayer;


import com.spring_boot2017127.demo.ModelLayer.User;
import com.spring_boot2017127.demo.ServiceRepository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class MainController {


    @Autowired
    private UserService userService;

    @RequestMapping(value ="/register",method = RequestMethod.GET)
    public String showRegistrationPage(Model model)
    {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model)
    {
        model.addAttribute("users", user);
        if(result.hasErrors())
        {return "registration";}
        else
        {userService.saveUser(user);
         model.addAttribute("message", "User account successfully");
        }
        return "index";
    }

    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/secure")
    public String secure()
    {
        return "secure";
    }

}
