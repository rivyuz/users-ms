package com.riviewz.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riviewz.dao.UserDao;
import com.riviewz.model.User;
import com.riviewz.service.UserService;
import com.riviewz.util.AesUtil;
import com.riviewz.util.EmailActivationCode;
import com.riviewz.util.JsonResponseBody;
import com.riviewz.util.JwtUtils;

@RestController
@RequestMapping("/user")
public class RegisterController {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserService userService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private EmailActivationCode emailService;
	
	@CrossOrigin
	@PostMapping("/register")
	public ResponseEntity<JsonResponseBody> register(@RequestParam(value="forward") String forward, @RequestParam(value="reverse") String reverse, @RequestParam(value="idle") String idle) throws UnsupportedEncodingException {

		AesUtil aesUtil = new AesUtil();
		String emailId = aesUtil.decrypt(forward);
		String passWord = aesUtil.decrypt(reverse);
		String userName = aesUtil.decrypt(idle);		
		
		String jwt = null;
		String registerResult = null;

		User userByEmailId = userService.findByEmailId(emailId);
		User userByName = userService.findByUserName(userName);
		
		//check if email id already exist
		if (null != userByEmailId) {
			registerResult = "emailExists";
			if (null != userByName) {
				registerResult = "bothExist";
			}
		} 
		
		else if (null != userByName) {
			registerResult = "userNameExists";
		}		
		
		else {
			
			UUID uniqueKey = UUID.randomUUID();
			
			User user = new User();
			user.setEmailId(emailId);
			user.setPassword(passWord);
			//user.setPassword(bCryptPasswordEncoder.encode(passWord));
			user.setUserName(userName);
			user.setDateRegistered(new Date());
			user.setStatus(0);
			user.setDob(null);
			user.setGender(null);
			user.setCitizen(null);
			user.setLiving(null);
			user.setEmailSent(0);
			user.setActivationCode(uniqueKey.toString());
			user.setPhotoUploaded(0);
			user.setPic(null);
			user.setType(null);
			user.setTempPwd(null);		
			user.setRole("ROLE_ADMIN");
			
			userDao.save(user);
			jwt = jwtUtils.createJwt(emailId, user.getId(), userName, new Date());
			
			try {
				emailService.sendActivationCode(user);
				registerResult = "regOkEmailSent";
			} catch (Exception e) {
				registerResult = "regOkEmailError";
				e.printStackTrace();
			}
		} //else ends
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Access-Control-Allow-Credentials","true")
				.header("Access-Control-Expose-Headers", "j")
				.header("j", jwt)
				.body(new JsonResponseBody(HttpStatus.OK.value(), registerResult));		
	}
	
	@CrossOrigin
	@RequestMapping("/confirmRegistration")
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