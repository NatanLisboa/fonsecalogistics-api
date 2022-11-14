package com.fonsecaworks.fonsecalogistics.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fonsecaworks.fonsecalogistics.domain.model.Delivery;
import com.fonsecaworks.fonsecalogistics.domain.repository.DeliveryRepository;
import com.fonsecaworks.fonsecalogistics.domain.service.DeliveryRequestService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/deliveries")
public class DeliveryController {

	private final DeliveryRepository deliveryRepository;
	private final DeliveryRequestService deliveryRequestService;
	
	@GetMapping
	public List<Delivery> findAllDeliveries() {
		return deliveryRepository.findAll();
	}
	
	@GetMapping("/{deliveryId}")
	public ResponseEntity<Delivery> findDeliveryById(@PathVariable Long deliveryId) {
		return deliveryRepository.findById(deliveryId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Delivery createDeliveryRequest(@Valid @RequestBody Delivery delivery) {
		return deliveryRequestService.createDeliveryRequest(delivery);
	}
}
