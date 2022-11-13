package com.fonsecaworks.fonsecalogistics.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fonsecaworks.fonsecalogistics.domain.model.Delivery;
import com.fonsecaworks.fonsecalogistics.domain.service.DeliveryRequestService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/deliveries")
public class DeliveryController {

	private DeliveryRequestService deliveryRequestService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Delivery createDeliveryRequest(@RequestBody Delivery delivery) {
		return deliveryRequestService.createDeliveryRequest(delivery);
	}
}
