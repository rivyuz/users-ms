package com.riviewz.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

public class Review {
	
	private int id;
	
	private String indexCatg;
	
	private String entityName;	
	
	private int entityFk;

	private int userFk;
	
	private String userName;
	
	private int photoUploaded;
	
	private String parameter_1;
	private String parameter_2;
	private String parameter_3;
	private String parameter_4;
	private String parameter_5;
	private String parameter_6;
	private String parameter_7;
	private String parameter_8;
	
	private String rating_1;
	private String rating_2;
	private String rating_3;
	private String rating_4;
	private String rating_5;
	private String rating_6;
	private String rating_7;
	private String rating_8;

	private float starRating;
	
	private Timestamp date;
	
	private String comment;
	
	private float rp_1;
	private float rp_2;
	private float rp_3;
	private float rp_4;
	private float rp_5;
	private float rp_6;
	private float rp_7;
	private float rp_8;

	private int port;
	
	private String dateStr;
	
    private byte[] pic;		
	
	private String userImage;
	
	private String type;
	
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIndexCatg() {
		return indexCatg;
	}

	public void setIndexCatg(String indexCatg) {
		this.indexCatg = indexCatg;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public int getEntityFk() {
		return entityFk;
	}

	public void setEntityFk(int entityFk) {
		this.entityFk = entityFk;
	}

	public int getUserFk() {
		return userFk;
	}

	public void setUserFk(int userFk) {
		this.userFk = userFk;
	}

	public String getParameter_1() {
		return parameter_1;
	}

	public void setParameter_1(String parameter_1) {
		this.parameter_1 = parameter_1;
	}

	public String getParameter_2() {
		return parameter_2;
	}

	public void setParameter_2(String parameter_2) {
		this.parameter_2 = parameter_2;
	}

	public String getParameter_3() {
		return parameter_3;
	}

	public void setParameter_3(String parameter_3) {
		this.parameter_3 = parameter_3;
	}

	public String getParameter_4() {
		return parameter_4;
	}

	public void setParameter_4(String parameter_4) {
		this.parameter_4 = parameter_4;
	}

	public String getParameter_5() {
		return parameter_5;
	}

	public void setParameter_5(String parameter_5) {
		this.parameter_5 = parameter_5;
	}

	public String getParameter_6() {
		return parameter_6;
	}

	public void setParameter_6(String parameter_6) {
		this.parameter_6 = parameter_6;
	}

	public String getParameter_7() {
		return parameter_7;
	}

	public void setParameter_7(String parameter_7) {
		this.parameter_7 = parameter_7;
	}

	public String getParameter_8() {
		return parameter_8;
	}

	public void setParameter_8(String parameter_8) {
		this.parameter_8 = parameter_8;
	}
	

	public String getRating_1() {
		return rating_1;
	}

	public void setRating_1(String rating_1) {
		this.rating_1 = rating_1;
	}

	public String getRating_2() {
		return rating_2;
	}

	public void setRating_2(String rating_2) {
		this.rating_2 = rating_2;
	}

	public String getRating_3() {
		return rating_3;
	}

	public void setRating_3(String rating_3) {
		this.rating_3 = rating_3;
	}

	public String getRating_4() {
		return rating_4;
	}

	public void setRating_4(String rating_4) {
		this.rating_4 = rating_4;
	}

	public String getRating_5() {
		return rating_5;
	}

	public void setRating_5(String rating_5) {
		this.rating_5 = rating_5;
	}

	public String getRating_6() {
		return rating_6;
	}

	public void setRating_6(String rating_6) {
		this.rating_6 = rating_6;
	}

	public String getRating_7() {
		return rating_7;
	}

	public void setRating_7(String rating_7) {
		this.rating_7 = rating_7;
	}

	public String getRating_8() {
		return rating_8;
	}

	public void setRating_8(String rating_8) {
		this.rating_8 = rating_8;
	}

	public float getStarRating() {
		return starRating;
	}

	public void setStarRating(float starRating) {
		this.starRating = starRating;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp timestamp) {
		this.date = timestamp;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public float getRp_1() {
		return rp_1;
	}

	public void setRp_1(float rp_1) {
		this.rp_1 = rp_1;
	}

	public float getRp_2() {
		return rp_2;
	}

	public void setRp_2(float rp_2) {
		this.rp_2 = rp_2;
	}

	public float getRp_3() {
		return rp_3;
	}

	public void setRp_3(float rp_3) {
		this.rp_3 = rp_3;
	}

	public float getRp_4() {
		return rp_4;
	}

	public void setRp_4(float rp_4) {
		this.rp_4 = rp_4;
	}

	public float getRp_5() {
		return rp_5;
	}

	public void setRp_5(float rp_5) {
		this.rp_5 = rp_5;
	}

	public float getRp_6() {
		return rp_6;
	}

	public void setRp_6(float rp_6) {
		this.rp_6 = rp_6;
	}

	public float getRp_7() {
		return rp_7;
	}

	public void setRp_7(float rp_7) {
		this.rp_7 = rp_7;
	}

	public float getRp_8() {
		return rp_8;
	}

	public void setRp_8(float rp_8) {
		this.rp_8 = rp_8;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}	


	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
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

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}	
}