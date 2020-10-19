package com.riviewz.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riviewz.model.Review;

@FeignClient(name="zuul-ms", url="${feign.zuul.url}")
public interface ReviewsProxy {

	@GetMapping("/reviews-ms/reviews/history")	
	public List<Review> getReviewsByUser(@RequestParam(value="j") String jwt) throws Exception;

	@PostMapping("/reviews-ms/reviews/delete")
	public String deleteReview(@RequestParam(value="category") String category, @RequestParam(value="entity_id") String entityFk, @RequestParam(value="entity_name") String entityName, @RequestParam(value="j") String jwt) throws Exception;

}