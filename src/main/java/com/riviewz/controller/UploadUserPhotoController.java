package com.riviewz.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.riviewz.service.ProfileService;

@RestController
public class UploadUserPhotoController {

	@Autowired
	private ProfileService profileService;

	/*----------AJAX call from myaccount.js----------*/
	@CrossOrigin
	@PostMapping("/account-info/photoupload")	
	public String uploadUserPhoto(@RequestParam(value="emailId") String emailId, @RequestParam(value="uploaded-photo") MultipartFile[] uploadedPhoto, HttpSession session) {
		
		String updateStatus = "invalid file";
		
		if (uploadedPhoto != null && uploadedPhoto.length > 0) {
			for (MultipartFile imgFile : uploadedPhoto) {
				if (!imgFile.getOriginalFilename().equals("")) {
					try {
					    
						InputStream inStream = imgFile.getInputStream();
						
					    Tika tika = new Tika();
					    String mimeType = tika.detect(inStream);						

						byte[] arrayPic = imgFile.getBytes();
						imgFile.getInputStream().read(arrayPic);
						
						
						if("image/png".equalsIgnoreCase(mimeType)) {
							profileService.uploadPhoto(emailId, arrayPic, "png");
							updateStatus = "image/png";
						} else if("image/jpg".equalsIgnoreCase(mimeType)) {
							profileService.uploadPhoto(emailId, arrayPic, "jpg");
							updateStatus = "image/jpeg";
						} else if("image/jpeg".equalsIgnoreCase(mimeType)) {
							profileService.uploadPhoto(emailId, arrayPic, "jpeg");
							updateStatus = "image/jpeg";
						} else {
							profileService.removePhoto(emailId, arrayPic, "null");
						}
						
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return updateStatus;
	}
	
}