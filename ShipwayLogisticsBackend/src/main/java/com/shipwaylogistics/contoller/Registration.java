package com.shipwaylogistics.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shipwaylogistics.model.Administrator;
import com.shipwaylogistics.model.Customer;
import com.shipwaylogistics.model.DeliveryPartner;
import com.shipwaylogistics.repository.AdministratorRepository;
import com.shipwaylogistics.repository.CustomerRepository;
import com.shipwaylogistics.repository.DeliveryPartnerRepository;

@RestController
@CrossOrigin
public class Registration {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DeliveryPartnerRepository deliveryPartnerRepository;
	
	@Autowired
	AdministratorRepository administratorRepository;
	
	@PostMapping("/addCustomer")
	public void registerCustomer(@RequestBody Customer customer) {
		customerRepository.save(customer);
	}
	
	@PostMapping("/addDeliveryPartner")
	public void registerDeliveryPartner(@RequestBody DeliveryPartner deliveryPartner) {
		System.out.println(deliveryPartner.getName() + " ___ " + deliveryPartner.getServices().get(0).getName()+ " ____ "+  deliveryPartner.getServices().get(0).getLocations());
		deliveryPartnerRepository.save(deliveryPartner);
	}
	
	@PostMapping("/addAdministrator")
	public void registerAdministrator(@RequestBody Administrator administrator) {
		administratorRepository.save(administrator);
	}
}
