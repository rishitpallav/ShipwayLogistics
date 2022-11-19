package com.shipwaylogistics.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shipwaylogistics.model.Review;

@Service
public class DeliveryPartnerService {

	
	public double updateDeliveryPartnerRatings(int deliveryPartnerId, List<Review> reviews) {
		int sumOfRating = 0;
		for(Review review : reviews) {
			sumOfRating += review.getRating();
		}
		return (sumOfRating/reviews.size());
	}
}
