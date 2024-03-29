package com.shipwaylogistics.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date dateBooked;
	private Date expectedDeliveryDate;
	private String status;
	private String fromAddress;
	private String fromCity;
	private String fromState;
	private int fromPincode;
	private String currentAddress;
	private String currentCity;
	private String currentState;
	private int currentPincode;
	private String toAddress;
	private String toCity;
	private String toState;
	private int toPincode;
	private String DeliveryPartnerName;
	@ManyToOne(targetEntity = Service.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "shipment_service_fk", referencedColumnName = "id")
	private Service service;
	@OneToOne(targetEntity = Payment.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "shipment_payment_fk", referencedColumnName = "id")
	private Payment payment;
	@ManyToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "shipment_customer_fk", referencedColumnName = "id")
	Customer customer;

	public Shipment() {

	}

	public Shipment(Date dateBooked, Date expectedDeliveryDate, String status, String fromAddress, String fromCity,
			String fromState, int fromPincode, String currentAddress, String currentCity, String currentState,
			int currentPincode, String toAddress, String toCity, String toState, int toPincode, Service service,
			Payment payment, Customer customer) {
		super();
		this.dateBooked = dateBooked;
		this.expectedDeliveryDate = expectedDeliveryDate;
		this.status = status;
		this.fromAddress = fromAddress;
		this.fromCity = fromCity;
		this.fromState = fromState;
		this.fromPincode = fromPincode;
		this.currentAddress = currentAddress;
		this.currentCity = currentCity;
		this.currentState = currentState;
		this.currentPincode = currentPincode;
		this.toAddress = toAddress;
		this.toCity = toCity;
		this.toState = toState;
		this.toPincode = toPincode;
		this.service = service;
		this.payment = payment;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateBooked() {
		return dateBooked;
	}

	public void setDateBooked(Date dateBooked) {
		this.dateBooked = dateBooked;
	}

	public String getDeliveryPartnerName() {
		return DeliveryPartnerName;
	}

	public void setDeliveryPartnerName(String deliveryPartnerName) {
		DeliveryPartnerName = deliveryPartnerName;
	}

	public Date getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getFromState() {
		return fromState;
	}

	public void setFromState(String fromState) {
		this.fromState = fromState;
	}

	public int getFromPincode() {
		return fromPincode;
	}

	public void setFromPincode(int fromPincode) {
		this.fromPincode = fromPincode;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public String getToState() {
		return toState;
	}

	public void setToState(String toState) {
		this.toState = toState;
	}

	public int getToPincode() {
		return toPincode;
	}

	public void setToPincode(int toPincode) {
		this.toPincode = toPincode;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public int getCurrentPincode() {
		return currentPincode;
	}

	public void setCurrentPincode(int currentPincode) {
		this.currentPincode = currentPincode;
	}

}
