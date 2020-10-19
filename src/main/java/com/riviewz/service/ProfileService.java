/*
 * Author: Siva 
 * Date  : 22-Jan-2015
 * Version: 1.0
 */

package com.riviewz.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riviewz.dao.ProfileDao;
import com.riviewz.model.User;

@Service
@Transactional
public class ProfileService {

	@Autowired
	public ProfileDao profileDao;
	
	public User getUserById(String userId) {
		return profileDao.findUserById(Integer.parseInt(userId));
	}	

	public int saveOrUpdateAccountInfo(String userId, String userName, String day, String month, String year, String citizenId, String livingId, String genderId) {
		
		String date = null;
		
		if ((("").equals(day) || ("").equals(month)) || (("dd").equals(day) || ("mm").equals(month))) {
			date = "1900" + "-" + "01" + "-" + "01";
		} else {

			if (("").equals(year) || ("yyyy").equals(year)) {
				date = "0000" + "-" + month + "-" + day;
			} else {
				date = year + "-" + month + "-" + day;
			}

		}

		String startDate = date;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

		Date insertDate = null;
		try {
			insertDate = sdf1.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		
		System.out.println("userName in class ProfileService Method saveOrUpdate() = " + userName);
		return profileDao.saveOrUpdateAccountInfo(userId, userName, insertDate, citizenId, livingId, genderId);
	}
	
	public void uploadPhoto(String emailId, byte[] arrayPic, String type) {
		profileDao.uploadPhoto(emailId, arrayPic, type);
	}
	
	public void removePhoto(String emailId, byte[] arrayPic, String type) {
		profileDao.removePhoto(emailId, arrayPic, type);
	}	
	
}