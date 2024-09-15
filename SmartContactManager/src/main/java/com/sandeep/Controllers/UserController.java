package com.sandeep.Controllers;


import com.sandeep.entities.UserEntity;
import com.sandeep.helper.Helper;
import com.sandeep.services.IUserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private IUserService userService ;


    //user dashboard page
    @RequestMapping(value = "/dashboard")
    public String userDashboard(Authentication authentication) {
        System.out.println("User dashboard");
        return "user/dashboard";
    }

    //user profile page
    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        System.out.println("Profile dashboard");

        return "user/profile";
    }

    // user add contact page

    // user view contact page

    // user edit contact page

    // user delete contact page

}
