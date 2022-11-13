package com.shipwaylogistics.contoller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shipwaylogistics.dto.GetShipment;
import com.shipwaylogistics.dto.IdentityDto;
import com.shipwaylogistics.dto.QuoteShipment;
import com.shipwaylogistics.dto.SendQuotation;
import com.shipwaylogistics.dto.SendShipment;
import com.shipwaylogistics.model.Customer;
import com.shipwaylogistics.model.DeliveryPartner;
import com.shipwaylogistics.model.Location;
import com.shipwaylogistics.model.Service;
import com.shipwaylogistics.model.Shipment;
import com.shipwaylogistics.repository.CustomerRepository;
import com.shipwaylogistics.repository.DeliveryPartnerRepository;
import com.shipwaylogistics.repository.LocationRepository;
import com.shipwaylogistics.repository.ServiceRepository;
import com.shipwaylogistics.repository.ShipmentRepository;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ShipmentRepository shipmentRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	DeliveryPartnerRepository deliveryPartnerRepository;

	@GetMapping("/getAllLocations")
	public List<Location> getLocations() {
		return locationRepository.findAll();
	}

	@PostMapping("/getQuote")
	public List<SendQuotation> getQuote(@RequestBody QuoteShipment quoteShipment) {
		List<Service> serviceList = serviceRepository.findAll();
		List<SendQuotation> result = new ArrayList<>();
		for (Service service : serviceList) {
			if (service.getMaxWeight() >= quoteShipment.getWeight()) {
				boolean fromLocationPresent = false;
				boolean toLocationPresent = false;
				for(Location location : service.getLocations()) {
					if(location.getZipcode() == quoteShipment.getFromLocation().getZipcode()) {
						fromLocationPresent = true;
					}
					if(location.getZipcode() == quoteShipment.getToLocation().getZipcode()) {
						toLocationPresent = true;
					}
				}					
				if (fromLocationPresent && toLocationPresent) {
					SendQuotation sendQuotation = new SendQuotation();
					sendQuotation.setServiceId(service.getId());
					sendQuotation.setPrice(service.getBasePrice() * quoteShipment.getQuantity());
					sendQuotation.setDiscount(service.getDiscount() * quoteShipment.getQuantity());
					sendQuotation.setServiceName(service.getName());
					sendQuotation.setDeliveryPartner(deliveryPartnerRepository
							.getReferenceById(serviceRepository.getDeliveryPartnerId(service.getId())).getName());
					result.add(sendQuotation);
				}
			}
		}
		return result;
	}

	@GetMapping("/getCustomerDetails")
	public Customer getCustomerDetails(@RequestBody int customerId) {
		return customerRepository.getReferenceById(customerId);
	}

	@PostMapping("/saveShipment")
	public int saveShipment(@RequestBody GetShipment getShipment) {
		Shipment shipment = new Shipment(getShipment.getDateBooked(), getShipment.getExpectedDeliveryDate(),
				getShipment.getStatus(), getShipment.getFromAddress(), getShipment.getFromCity(),
				getShipment.getFromState(), getShipment.getFromPincode(), getShipment.getToAddress(),
				getShipment.getToCity(), getShipment.getToState(), getShipment.getToPincode());
		shipment.setCustomer(customerRepository.getReferenceById(getShipment.getCustomerId()));
		shipment.setPayment(getShipment.getPayment());
		shipment.setService(serviceRepository.getReferenceById(getShipment.getServiceId()));
		return shipmentRepository.save(shipment).getId();
	}

	@PostMapping("/customerShipments")
	public List<Shipment> getCustomerShipments(@RequestBody IdentityDto identityDto) {
		List<Shipment> customerShipments = new ArrayList<>();
		List<Shipment> shipments = shipmentRepository.findAll();
		for (Shipment shipment : shipments) {
			if (shipment.getCustomer().getId() == identityDto.getId()) {
				customerShipments.add(shipment);
			}
		}
		return customerShipments;
	}

	@PostMapping("/trackPackage")
	public SendShipment getTrackingDetails(@RequestBody IdentityDto identityDto) {
		Shipment shipment = shipmentRepository.getReferenceById(identityDto.getId());
		SendShipment sendShipment = new SendShipment(shipment.getDateBooked(), shipment.getExpectedDeliveryDate(),
				shipment.getStatus(), shipment.getFromAddress(), shipment.getFromCity(), shipment.getFromState(),
				shipment.getFromPincode(), shipment.getToAddress(), shipment.getToCity(), shipment.getToState(),
				shipment.getToPincode());
		Service service = serviceRepository.getReferenceById(shipment.getService().getId());
		sendShipment.setService(service.getName());
		sendShipment.setDeliveryPartner(deliveryPartnerRepository
				.getReferenceById(serviceRepository.getDeliveryPartnerId(service.getId())).getName());
		return sendShipment;
	}

}
