package com.sandeep.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserFormReqDTO {

	@NotBlank(message = "Username required")
	@Size(min = 3, message = "Min 3 Character is requied")
	private String name ;
	@Email(message = "Invalid email address")
	private String email;
	@Size(min = 6, message = "Min 6 Character requried")
	private String password;
	@NotBlank(message = "About is requried")
	private String about ;
	@Size(min = 8,max = 12, message = "Invalid Phone Number")
	private String phoneNumber;

}
