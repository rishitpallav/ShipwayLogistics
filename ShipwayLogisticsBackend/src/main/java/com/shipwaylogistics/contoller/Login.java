package com.shipwaylogistics.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shipwaylogistics.dto.LoginObject;
import com.shipwaylogistics.model.Administrator;
import com.shipwaylogistics.model.Customer;
import com.shipwaylogistics.model.DeliveryPartner;
import com.shipwaylogistics.repository.AdministratorRepository;
import com.shipwaylogistics.repository.CustomerRepository;
import com.shipwaylogistics.repository.DeliveryPartnerRepository;

@RestController
@CrossOrigin
public class Login {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AdministratorRepository administratorRepository;
	
	@Autowired
	DeliveryPartnerRepository deliveryPartnerRepository;
	
	@RequestMapping("")
	public String welcome() {
		return "welcome";
	}
	
	@PostMapping("/customerLogin")
	public String customerLogin(@RequestBody LoginObject loginObject) {
		Customer customer = customerRepository.findByEmail(loginObject.getEmail());
		if(customer.getPassword().equals(loginObject.getPassword())) {
			System.out.println("Found Customer with id " + customer.getId());
			return customer.getFirstName();
		}
		System.out.println("Couldn't find customer");
		return null;
	}
	
	@PostMapping("/adminLogin")
	public String adminLogin(@RequestBody LoginObject loginObject) {
		Administrator administrator = administratorRepository.findByEmail(loginObject.getEmail());
		if(administrator.getPassword().equals(loginObject.getPassword())) {
			System.out.println("Found Customer with id " + administrator.getId());
			return administrator.getName();
		}
		System.out.println("Couldn't find customer");
		return null;
	}
	
	@PostMapping("/deliveryPartnerLogin")
	public String deliveryPartnerLogin(@RequestBody LoginObject loginObject) {
		DeliveryPartner deliveryPartner = deliveryPartnerRepository.findByEmail(loginObject.getEmail());
		if(deliveryPartner.getPassword().equals(loginObject.getPassword())) {
			return deliveryPartner.getName();
		}
		return null;
	}
}
