package com.riviewz.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riviewz.service.UserService;
import com.riviewz.util.JsonResponseBody;

@RestController
@RequestMapping("/user")
public class LogoutController {
	
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserService userService;
	
	@CrossOrigin
	@PostMapping("/logout")
	public ResponseEntity<JsonResponseBody> logout(@RequestParam(value="j") String jwt) throws UnsupportedEncodingException{
		
		String logoutResult = "user logged out";
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Access-Control-Allow-Credentials","true")
				.header("Access-Control-Expose-Headers", "j")
				.header("j", (String)null)
				.body(new JsonResponseBody(HttpStatus.OK.value(), logoutResult));
	}	

} //class ends