package com.sandeep.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class ContactReqDto {


        @NotBlank(message = "Name is required")
        private String name;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid Email Address [ example@gmail.com ]")
        private String email;

        @NotBlank(message = "Phone Number is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Phone Number")
        private String phoneNumber;

        @NotBlank(message = "Address is required")
        private String address;

        private String description;

        private boolean favorite;

        private String websiteLink;

        private String linkedInLink;

        // annotation create karenge jo file validate
        // size
        // resolution


        private MultipartFile contactImage;

        private String picture;

    }