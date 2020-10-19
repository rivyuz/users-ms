package com.riviewz.controller;

import java.util.Map;

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
import com.riviewz.util.EmailFromFooter;
import com.riviewz.util.JsonResponseBody;
import com.riviewz.util.JwtUtils;

@RestController
@RequestMapping("/footer")
public class FooterController {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserService userService;

	@Autowired
	private EmailFromFooter emailService;
	
	@CrossOrigin
	@PostMapping("/mail")
	public ResponseEntity<JsonResponseBody> footerMail(@RequestParam(value="emailDest") String emailDest, @RequestParam(value="emailSubject") String emailSubject, @RequestParam(value="emailContent") String emailContent, @RequestParam(value="j") String jwt) throws Exception {

		String footerMailResult = null;
		
		Map<String, Object> userData = jwtUtils.verifyJwtAndGetData(jwt);
		int userId = (int) userData.get("userId");		
		User user = userService.getUserById(String.valueOf(userId));
		
		try {
			emailService.footerMail(user.getEmailId(), emailDest, emailSubject, emailContent);
			footerMailResult = "emailSent";
		} catch (Exception e) {
			footerMailResult = "emailError";
			e.printStackTrace();
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Access-Control-Allow-Credentials","true")
				.header("Access-Control-Expose-Headers", "j")
				.header("j", jwt)
				.body(new JsonResponseBody(HttpStatus.OK.value(), footerMailResult));

	}
	
} //class ends