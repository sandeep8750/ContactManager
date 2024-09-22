package com.sandeep.Controllers;

import com.sandeep.entities.Contact;
import com.sandeep.entities.UserEntity;
import com.sandeep.helper.Color;
import com.sandeep.helper.Helper;
import com.sandeep.helper.Message;
import com.sandeep.repositories.IContactRepository;
import com.sandeep.requestDto.ContactReqDto;
import com.sandeep.services.IContactService;
import com.sandeep.services.IUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    private final IUserService userService;
    @Autowired
    private final IContactRepository contactRepository;
    @Autowired
    private IContactService contactService;



    private Logger logger = LoggerFactory.getLogger(ContactController.class);
    //save contacts
    @GetMapping("/add")
    public String saveContact(Model model){
        ContactReqDto contactReqDto = new ContactReqDto();
        model.addAttribute("ContactReqDto",contactReqDto);
        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute("ContactReqDto")  ContactReqDto ContactReqDto, BindingResult result,
                              Authentication authentication, HttpSession session) {

        // 1 validate form
        if (result.hasErrors()) {

            result.getAllErrors().forEach(error -> logger.info(error.toString()));

            session.setAttribute("message", Message.builder()
                    .content("Please correct the following errors")
                    .color(Color.red).build());
            return "user/add_contact";
        }

        String username = Helper.getUserName(authentication);
        // form ---> contact

        UserEntity user = userService.getUserByEmail(username);
        // 2 process the contact picture

        // image process

        // uplod karne ka code
        Contact contact = new Contact();
        contact.setName(ContactReqDto.getName());
        contact.setFavorite(ContactReqDto.isFavorite());
        contact.setEmail(ContactReqDto.getEmail());
        contact.setPhoneNumber(ContactReqDto.getPhoneNumber());
        contact.setAddress(ContactReqDto.getAddress());
        contact.setDescription(ContactReqDto.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(ContactReqDto.getLinkedInLink());
        contact.setWebsiteLink(ContactReqDto.getWebsiteLink());


        contactService.save(contact);
        System.out.println(ContactReqDto);

        // 3 set the contact picture url

        // 4 `set message to be displayed on the view

        session.setAttribute("message",
                Message.builder()
                        .content("You have successfully added a new contact")
                        .color(Color.green)
                        .build());

        return "redirect:/user/contacts/add";

    }

}
