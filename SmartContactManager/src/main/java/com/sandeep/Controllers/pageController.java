package com.sandeep.Controllers;

import com.sandeep.entities.User;
import com.sandeep.helper.Color;
import com.sandeep.helper.Message;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.sandeep.forms.UserFormReqDTO;
import com.sandeep.services.IUserService;

@Controller
public class pageController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/home")
    public String home(Model model) {
        return "home";
    }

    //about route
    @RequestMapping("/about")
    public String aboutPage() {
        System.out.println("about page loading boss ");
        return "about";
    }

    //service
    @RequestMapping("/services")
    public String servicePage() {
        System.out.println("service page loading boss ");
        return "services";
    }

    //Contact
    @RequestMapping("/contact")
    public String contact() {
        System.out.println("service page loading boss ");
        return "contact";
    }

    // login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping
    public String login(String s){
        return "login";
    }


    // Register
    @GetMapping("/register")
    public String register(Model model) {
        UserFormReqDTO userFormReqDTO = new UserFormReqDTO();
        model.addAttribute("userFormReqDTO", userFormReqDTO);
        return "register";
    }


    // processing register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(Model model ,@Valid @ModelAttribute(name = "userFormReqDTO") UserFormReqDTO UserFormReqDTO , BindingResult result, HttpSession session) {

        if(result.hasErrors())
        {
            return "register";
        }
        User user = new User();

        user.setName(UserFormReqDTO.getName());
        user.setEmail((UserFormReqDTO.getEmail()));
        user.setPassword(UserFormReqDTO.getPassword());
        user.setAbout(UserFormReqDTO.getAbout());
        user.setPhoneNumber(UserFormReqDTO.getPhoneNumber());
        user.setProfilePicLink("https://previews.123rf.com/images/aquir/aquir1311/aquir131100316/23569861-sample-grunge-red-round-stamp.jpg");

        userService.saveUser(user);

        Message message = Message.builder()
                .content("Registration Successfully Bosss....")
                .color(Color.blue)
                .build();

        session.setAttribute("message",message);

        System.out.println("User Saved Successfully.....");
        return "redirect:/register";
    }


}
