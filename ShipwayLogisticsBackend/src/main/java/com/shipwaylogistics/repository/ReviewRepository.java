package com.shipwaylogistics.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shipwaylogistics.model.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {

	public List<Review> findByUserId(int userId);

	public List<Review> findByDeliveryPartnerId(int deliveryPartnerId);

	public List<Review> findFirst3ByDeliveryPartnerIdOrderByShippedDateDesc(int deliveryPartnerId);
}
