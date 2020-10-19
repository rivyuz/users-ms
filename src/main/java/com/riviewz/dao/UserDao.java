package com.riviewz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.riviewz.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	public User findUserById(int userId);
	
	public User findByEmailId(String emailId);
	
	public User findByUserName(String userName);
	
	public User findUserByActivationCode(String code);
	
	@Modifying
	@Query(value="DELETE FROM _user_detail WHERE id = ?1", nativeQuery = true)
	@Transactional
	public int deleteAccount(int userId );
	
	@Modifying
	@Query(value="UPDATE _user_detail SET status = 1 WHERE activation_code = ?1", nativeQuery = true)
	@Transactional
	public int activateUser(String code);
	
	@Modifying
	@Query(value="UPDATE _user_detail SET temp_pwd = ?2 WHERE email_id = ?1", nativeQuery = true)
	@Transactional
	public void saveTemporaryPassword(String emailId, String tempPassword);
	
	@Modifying
	@Query(value="UPDATE _user_detail SET password = ?2 WHERE email_id = ?1", nativeQuery = true)
	@Transactional
	public void saveNewPassword(String emailId, String newPassword);
	
	@Modifying
	@Query(value="UPDATE _user_detail SET logged_in_status = ?2 WHERE email_id = ?1", nativeQuery = true)
	@Transactional
	public void saveLoggedInStatus(String emailId, int status);	
	
}