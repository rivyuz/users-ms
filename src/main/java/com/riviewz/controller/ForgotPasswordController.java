package com.riviewz.controller;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riviewz.model.User;
import com.riviewz.service.UserService;
import com.riviewz.util.AesUtil;
import com.riviewz.util.EmailPassword;
import com.riviewz.util.JsonResponseBody;

@RestController
@RequestMapping("/user")
public class ForgotPasswordController {
	
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserService userService;

	@Autowired
	private EmailPassword emailService;
	
	private static final Random RANDOM = new SecureRandom();
	/** Length of password. @see #generateRandomPassword() */
	public static final int PASSWORD_LENGTH = 8;	
	
	@CrossOrigin
	@PostMapping("/forgot")
	public ResponseEntity<JsonResponseBody> forgot(@RequestParam(value="forward") String forward) throws UnsupportedEncodingException{

		String tempPassword = "";
		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
		
		AesUtil aesUtil = new AesUtil();
		String emailId = aesUtil.decrypt(forward);
		
		String jwt = null;
		String forgotResult = null;

		User user = userService.findByEmailId(emailId);

		//check if email id exist
		if (null != user) {
			try {
				for (int j = 0; j < PASSWORD_LENGTH; j++) {
					int index = (int) (RANDOM.nextDouble() * letters.length());
					tempPassword += letters.substring(index, index + 1);
				}				
				userService.saveTemporaryPassword(user.getEmailId(), tempPassword);
				emailService.sendOneTimePassword(user, tempPassword);
				forgotResult = "otpOkEmailSent";
			} catch (Exception e) {
				forgotResult = "otpOkEmailError";
				e.printStackTrace();
			}			
		} else {
			
			forgotResult = "invalid user";
			
		} //else ends
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Access-Control-Allow-Credentials","true")
				.header("Access-Control-Expose-Headers", "j")
				.header("j", jwt)
				.body(new JsonResponseBody(HttpStatus.OK.value(), forgotResult));

	}

} //class ends