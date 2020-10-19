package com.riviewz.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riviewz.dao.UserDao;
import com.riviewz.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	UserDao userDao;
	
	public User findByEmailId(String emailId) {
		return userDao.findByEmailId(emailId);		
	}
	
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);		
	}

	public User getUserById(String userId) {
		return userDao.findUserById(Integer.parseInt(userId));
	}
		
	public int deleteAccount(int userId) {
		return userDao.deleteAccount(userId);
	}
	
	public int activateUser(String code) {
		return userDao.activateUser(code);
	}
	
	public User getUserByActivationCode(String code) {
		return userDao.findUserByActivationCode(code);
	}
	
	public void saveTemporaryPassword(String emailId, String tempPassword) {
		userDao.saveTemporaryPassword(emailId, tempPassword);
	}
	
	public void saveNewPassword(String emailId, String newPassword) {
		userDao.saveNewPassword(emailId, newPassword);
	}
	
	public void saveLoggedInStatus(String emailId, int status) {
		userDao.saveLoggedInStatus(emailId, status);
	}	
}