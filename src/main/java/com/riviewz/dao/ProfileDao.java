/*
 * Author: Siva 
 * Date  : 22-Jan-2015
 * Version: 1.0
 */

package com.riviewz.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.riviewz.model.User;

@Repository
public interface ProfileDao extends JpaRepository<User, Integer> {
	
	User findUserById(int userId);

	@Modifying
	@Query(value="UPDATE _user_detail SET user_name = :userName, dob = :insertDate, citizen = :citizenId, living = :livingId, gender = :genderId WHERE id = :userId", nativeQuery = true)	
	public int saveOrUpdateAccountInfo(@Param("userId")String userId, @Param("userName")String userName, @Param("insertDate")Date insertDate, @Param("citizenId")String citizenId, @Param("livingId")String livingId, @Param("genderId")String genderId);
	
	@Modifying
	@Query(value="UPDATE _user_detail SET pic = :arrayPic, type = :type, photo_uploaded = 1 WHERE email_id = :emailId", nativeQuery = true)
	public void uploadPhoto(@Param("emailId")String emailId, @Param("arrayPic")byte[] arrayPic, @Param("type")String type);
	
	@Modifying
	@Query(value="UPDATE _user_detail SET pic = :arrayPic, type = :type, photo_uploaded = 0 WHERE email_id = :emailId", nativeQuery = true)
	public void removePhoto(@Param("emailId")String emailId, @Param("arrayPic")byte[] arrayPic, @Param("type")String type);	

}

