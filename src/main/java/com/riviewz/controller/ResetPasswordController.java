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
public class ResetPasswordController {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserService userService;

	@CrossOrigin
	@PostMapping("/resetpassword")
	public ResponseEntity<JsonResponseBody> resetPassword(@RequestParam(value="forward") String forward, @RequestParam(value="reverse") String reverse, @RequestParam(value="idle") String idle) throws UnsupportedEncodingException {
		
		AesUtil aesUtil = new AesUtil();
		String emailId = aesUtil.decrypt(forward);
		String oneTimePassword = aesUtil.decrypt(reverse);
		String newPassword = aesUtil.decrypt(idle);		
		
		String jwt = null;
		String resetPasswordResult = "reset error";

		User userByEmailId = userService.findByEmailId(emailId);
		
		if(null != userByEmailId.getTempPwd() && userByEmailId.getTempPwd().equals(oneTimePassword)) {
			userService.saveNewPassword(emailId, newPassword);
			userService.saveTemporaryPassword(emailId, null);
			resetPasswordResult = "reset successful";
			jwt = jwtUtils.createJwt(emailId, userByEmailId.getId(), userByEmailId.getUserName(), new Date());
		} else {
			resetPasswordResult = "invalid temp password";
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Access-Control-Allow-Credentials","true")
				.header("Access-Control-Expose-Headers", "j")
				.header("j", jwt)
				.body(new JsonResponseBody(HttpStatus.OK.value(), resetPasswordResult));		
	}
	
} //class ends