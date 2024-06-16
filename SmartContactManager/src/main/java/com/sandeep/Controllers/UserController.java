package com.sandeep.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    //user dashboard page
    @GetMapping("/dashboard")
    public String userDashboard() {
        System.out.println("userDashboard");
        return "user/dashboard";
    }

    //user profile page
    @GetMapping("/profile")
    public String userProfile() {
        System.out.println("userDashboard");
        return "user/profile";
    }

    // user add contact page

    // user view contact page

    // user edit contact page

    // user delete contact page

}
