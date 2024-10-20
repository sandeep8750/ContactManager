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
import com.sandeep.services.ImageService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    private final IUserService userService;
    @Autowired
    private final IContactRepository contactRepository;
    @Autowired
    private IContactService contactService;
    @Autowired
    private ImageService imageService;



    private Logger logger = LoggerFactory.getLogger(ContactController.class);
    //save contacts
    @GetMapping("/add")
    public String saveContact(Model model){
        ContactReqDto contactReqDto = new ContactReqDto();
        model.addAttribute("ContactReqDto",contactReqDto);
        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute("ContactReqDto")  ContactReqDto contactReqDto, BindingResult result,
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
        String processedImageUr  =  imageService.uploadImage(contactReqDto.getContactImage(),contactReqDto.getContactImage().getOriginalFilename());

        // uplod karne ka code
        Contact contact = new Contact();
        contact.setName(contactReqDto.getName());
        contact.setFavorite(contactReqDto.isFavorite());
        contact.setEmail(contactReqDto.getEmail());
        contact.setPhoneNumber(contactReqDto.getPhoneNumber());
        contact.setAddress(contactReqDto.getAddress());
        contact.setDescription(contactReqDto.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactReqDto.getLinkedInLink());
        contact.setWebsiteLink(contactReqDto.getWebsiteLink());
        // 3 set the contact picture url
        if (contactReqDto.getContactImage() != null && !contactReqDto.getContactImage().isEmpty()) {
            String filename = UUID.randomUUID().toString();
            String fileURL = imageService.uploadImage(contactReqDto.getContactImage(), filename);
            contact.setPicture(fileURL);
            System.out.println(filename);
            contact.setCloudinaryImagePublicId(filename);

        }
        //save contact
        contactService.save(contact);
        //  contactService.save(contact);
        System.out.println(contactReqDto);


        // 4 `set message to be displayed on the view

        session.setAttribute("message",
                Message.builder()
                        .content("You have successfully added a new contact")
                        .color(Color.green)
                        .build());

        return "redirect:/user/contacts/add";

    }


    @RequestMapping
    public String viewContacts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction, Model model,
            Authentication authentication) {

        // load all the user contacts
        String username = Helper.getUserName(authentication);

        UserEntity user = userService.getUserByEmail(username);

        Page<Contact> pageContact = contactService.getById(user, page, size, sortBy, direction);

        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        model.addAttribute("contactSearchForm", new ContactSearchForm());

        return "user/contacts";
    }

}
