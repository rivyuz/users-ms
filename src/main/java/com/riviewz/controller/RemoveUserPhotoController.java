package com.riviewz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riviewz.service.ProfileService;

@RestController
public class RemoveUserPhotoController {

	@Autowired
	private ProfileService profileService;

	/*----------AJAX call from myaccount.js----------*/
	@CrossOrigin
	@PostMapping("/account-info/removephoto")	
	public String removeUserPhoto(@RequestParam(value="emailId") String emailId, HttpSession session) {
		
		String updateStatus = "null";

		profileService.removePhoto(emailId, null, null);
		
		return updateStatus;
	}
	
}