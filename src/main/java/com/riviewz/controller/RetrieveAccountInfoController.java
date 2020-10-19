package com.riviewz.controller;

import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riviewz.model.User;
import com.riviewz.service.ProfileService;
import com.riviewz.util.JwtUtils;

@RestController
@RequestMapping("/account-info")
public class RetrieveAccountInfoController {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private ProfileService profileService;

	/*----------AJAX call from myaccount.js----------*/
	@CrossOrigin
	@GetMapping("/retrieve")	
	public User getUserDetails(@RequestParam(value="j") String jwt, HttpSession session, HttpServletResponse response) throws Exception {

		Map<String, Object> userData = jwtUtils.verifyJwtAndGetData(jwt);
		
		int userId = (int) userData.get("userId");
		
		User userDetail = (User) profileService.getUserById(String.valueOf(userId));
		
		if(null != userDetail.getPic()) {
		    byte[] encodeBase64 = Base64.getEncoder().encode(userDetail.getPic());
		    String base64Encoded = new String(encodeBase64, "UTF-8");
		    userDetail.setUserImage(base64Encoded);
		}

		return userDetail;
	}	

}