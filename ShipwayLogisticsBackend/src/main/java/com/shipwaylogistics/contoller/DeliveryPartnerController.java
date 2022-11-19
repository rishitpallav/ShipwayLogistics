package com.shipwaylogistics.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shipwaylogistics.dto.IdentityDto;
import com.shipwaylogistics.dto.PartnerService;
import com.shipwaylogistics.model.DeliveryPartner;
import com.shipwaylogistics.model.Review;
import com.shipwaylogistics.model.Service;
import com.shipwaylogistics.model.Shipment;
import com.shipwaylogistics.repository.DeliveryPartnerRepository;
import com.shipwaylogistics.repository.ReviewRepository;
import com.shipwaylogistics.repository.ServiceRepository;
import com.shipwaylogistics.repository.ShipmentRepository;

@RestController
@CrossOrigin
public class DeliveryPartnerController {

	@Autowired
	DeliveryPartnerRepository deliverPartnerRepository;
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	ShipmentRepository shipmentRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@PostMapping("/addService")
	public void addService(@RequestBody PartnerService partnerService) {
		DeliveryPartner deliveryPartner = deliverPartnerRepository.getReferenceById(partnerService.getId());
		List<Service> services = deliveryPartner.getServices();
		services.add(partnerService.getService());
		deliveryPartner.setServices(services);
		deliverPartnerRepository.save(deliveryPartner);
	}
	
	@PostMapping("/modifyService")
	public void modifyService(@RequestBody PartnerService partnerService) {
		DeliveryPartner deliveryPartner = deliverPartnerRepository.getReferenceById(partnerService.getId());
		List<Service> services = deliveryPartner.getServices();
		services.remove(partnerService.getId());
		services.add(partnerService.getService());
		deliveryPartner.setServices(services);
		deliverPartnerRepository.save(deliveryPartner);
	}
	
	@PostMapping("/removeService")
	public void removeService(@RequestBody PartnerService partnerService) {
		DeliveryPartner deliveryPartner = deliverPartnerRepository.getReferenceById(partnerService.getId());
		List<Service> services = deliveryPartner.getServices();
		services.remove(partnerService.getId());
		deliveryPartner.setServices(services);
		deliverPartnerRepository.save(deliveryPartner);
	}
	
	@PostMapping("/partnerShipments")
	public List<Shipment> partnerShipments(@RequestBody IdentityDto identityDto) {
		List<Service> servicesOfDeliveryPartner = deliverPartnerRepository.getReferenceById(identityDto.getId()).getServices();
		List<Shipment> shipments =  shipmentRepository.findAll();
		List<Shipment> shipmentsOfDeliveryPartner = new ArrayList<>();
		for(Shipment shipment : shipments) {
			if(servicesOfDeliveryPartner.contains(shipment.getService())) {
				shipmentsOfDeliveryPartner.add(shipment);
			}
		}
		return shipmentsOfDeliveryPartner;
	}
	
	@PostMapping("/acceptShipment")
	public boolean acceptShipment(@RequestBody IdentityDto identityDto) {
		Shipment shipment = shipmentRepository.getReferenceById(identityDto.getId());
		shipment.setStatus("Accepted");
		Shipment savedShipment = shipmentRepository.save(shipment);
		if(savedShipment.equals(null)) {
			return false;
		}
		return true;
	}
	
	@GetMapping("/getAllReviews")
	public List<Review> getAllReviews(@RequestBody IdentityDto identityDto) {
		return reviewRepository.findByUserId(identityDto.getId());
	}
	
	@GetMapping("/getRecentReviews")
	public List<Review> getThreeMostRecentReviews(@RequestBody IdentityDto identityDto) {
		return reviewRepository.findFirst3ByDeliveryPartnerIdOrderByShippedDateDesc(identityDto.getId());
	}
}
