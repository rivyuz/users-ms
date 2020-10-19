package com.riviewz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riviewz.feign.ReviewsProxy;
import com.riviewz.model.Review;
import com.riviewz.model.User;
import com.riviewz.service.UserService;
import com.riviewz.util.JsonResponseBody;
import com.riviewz.util.JwtUtils;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewsProxy reviewsProxy;

	@CrossOrigin
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") String id) {
		
		return userService.getUserById(id);

	}
	
	@CrossOrigin
	@PostMapping("/delete")
	public ResponseEntity<JsonResponseBody> deleteAccount(@RequestParam(value="j") String jwt) throws Exception{
		
		Map<String, Object> userData = jwtUtils.verifyJwtAndGetData(jwt);
		
		int id = (int) userData.get("userId");
		
		List<Review> revList = reviewsProxy.getReviewsByUser(jwt);
		
		for(Review rev : revList) {
			reviewsProxy.deleteReview(rev.getIndexCatg(), String.valueOf(rev.getEntityFk()), rev.getEntityName(), jwt);
		}
		
		int deleteStatus = userService.deleteAccount(id);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Access-Control-Allow-Credentials","true")
				.header("Access-Control-Expose-Headers", "j")
				.header("j", (String)null)
				.body(new JsonResponseBody(HttpStatus.OK.value(), deleteStatus));
		
	}	

} //class ends