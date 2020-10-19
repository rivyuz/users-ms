package com.riviewz.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

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
import com.riviewz.util.JsonResponseBody;
import com.riviewz.util.JwtUtils;

@RestController
@RequestMapping("/user")
public class LoginController {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserService userService;

	@CrossOrigin
	@PostMapping("/login")
	public ResponseEntity<JsonResponseBody> login(@RequestParam(value="forward") String forward, @RequestParam(value="reverse") String reverse) throws UnsupportedEncodingException{
		
		AesUtil aesUtil = new AesUtil();
		String emailId = aesUtil.decrypt(forward);
		String passWord = aesUtil.decrypt(reverse);
		
		String jwt = null;
		String loginResult = null;		

		User user = userService.findByEmailId(emailId);

		//check if the credentials are valid
		if (null != user) {
			if (user.getEmailId().equalsIgnoreCase(emailId) && user.getPassword().equals(passWord)) {
				loginResult = "valid credentials";
				jwt = jwtUtils.createJwt(emailId, user.getId(), user.getUserName(), new Date());
			} else {
				loginResult ="invalid credentials";
			}
		} else {
			loginResult ="invalid user";
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Access-Control-Allow-Credentials","true")
				.header("Access-Control-Expose-Headers", "j")
				//.header("Access-Control-Allow-Origin", "*")
				.header("j", jwt)
				.body(new JsonResponseBody(HttpStatus.OK.value(), loginResult));		

	}
	
	@CrossOrigin
	@RequestMapping("/resetPassword")
	public ResponseEntity<JsonResponseBody> confirmRegistration(@RequestParam(value="code") String code) throws UnsupportedEncodingException {		

		String jwt = null;
		String registrationConfirmed = "false";

		int userActive = userService.activateUser(code);

		User usr = null;

		if (userActive == 1) {
			registrationConfirmed = "true";
			usr = userService.getUserByActivationCode(code);
			jwt = jwtUtils.createJwt(usr.getEmailId(), usr.getId(), usr.getUserName(), new Date());
		} else {
			registrationConfirmed = "false";
		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Access-Control-Allow-Credentials","true")
				.header("Access-Control-Expose-Headers", "j")
				.header("j", jwt)
				.body(new JsonResponseBody(HttpStatus.OK.value(), registrationConfirmed));		
	}	

} //class ends