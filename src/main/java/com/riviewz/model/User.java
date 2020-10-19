package com.riviewz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "_user_detail")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "user_name")
	private String userName;
	
	private String password;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_registered")
	private Date dateRegistered;
	
	private String role;
	
	private int status;	

	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	private Date dob;

	private String gender;

	private String citizen;

	private String living;

	@Column(name = "email_sent")
	private int emailSent;
	
	private String activationCode;

	@Column(name = "photo_uploaded")
	private int photoUploaded;

	@Lob
    @Column(name="pic")
    private byte[] pic;
	
    @Column(name = "type")
	private String type;
	
    @Column(name = "temp_pwd")
	private String tempPwd;
    
    @Column(name = "logged_in_status")
	private int loggedInStatus;    
	
	@Transient
	private String day;
	@Transient
	private String month;
	@Transient
	private String year;
	@Transient
	private String userImage;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getDateRegistered() {
		return dateRegistered;
	}
	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getCitizen() {
		return citizen;
	}
	public void setCitizen(String citizen) {
		this.citizen = citizen;
	}
	
	public String getLiving() {
		return living;
	}
	public void setLiving(String living) {
		this.living = living;
	}
	
	public int getEmailSent() {
		return emailSent;
	}
	public void setEmailSent(int emailSent) {
		this.emailSent = emailSent;
	}
	
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	
	public int getPhotoUploaded() {
		return photoUploaded;
	}
	public void setPhotoUploaded(int photoUploaded) {
		this.photoUploaded = photoUploaded;
	}
	
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTempPwd() {
		return tempPwd;
	}
	public void setTempPwd(String tempPwd) {
		this.tempPwd = tempPwd;
	}
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public int getLoggedInStatus() {
		return loggedInStatus;
	}
	public void setLoggedInStatus(int loggedInStatus) {
		this.loggedInStatus = loggedInStatus;
	}
}