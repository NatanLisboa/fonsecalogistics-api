package com.fonsecaworks.fonsecalogistics.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fonsecaworks.fonsecalogistics.api.dto.DeliveryDTO;
import com.fonsecaworks.fonsecalogistics.api.dto.input.DeliveryInput;
import com.fonsecaworks.fonsecalogistics.api.mapper.DeliveryModelMapper;
import com.fonsecaworks.fonsecalogistics.domain.model.Delivery;
import com.fonsecaworks.fonsecalogistics.domain.repository.DeliveryRepository;
import com.fonsecaworks.fonsecalogistics.domain.service.DeliveryCheckoutService;
import com.fonsecaworks.fonsecalogistics.domain.service.DeliveryRequestService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/deliveries")
public class DeliveryController {

	private final DeliveryRepository deliveryRepository;
	private final DeliveryRequestService deliveryRequestService;
	private final DeliveryCheckoutService deliveryCheckoutService;
	private final DeliveryModelMapper deliveryModelMapper;
	
	@GetMapping
	public List<DeliveryDTO> findAllDeliveries() {
		return deliveryModelMapper.toDTOList(deliveryRepository.findAll());
	}
	
	@GetMapping("/{deliveryId}")
	public ResponseEntity<DeliveryDTO> findDeliveryById(@PathVariable Long deliveryId) {
		return deliveryRepository.findById(deliveryId)
				.map(delivery -> {
					DeliveryDTO deliveryDTO = deliveryModelMapper.toDTO(delivery);
					return ResponseEntity.ok(deliveryDTO);
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DeliveryDTO createDeliveryRequest(@Valid @RequestBody DeliveryInput deliveryInput) {
		Delivery newDelivery = deliveryModelMapper.toEntity(deliveryInput);
		Delivery requestedDelivery = deliveryRequestService.createDeliveryRequest(newDelivery);
		return deliveryModelMapper.toDTO(requestedDelivery);
	}
	
	@PutMapping("/{deliveryId}/checkout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void checkoutDelivery(@PathVariable Long deliveryId) {
		deliveryCheckoutService.checkoutDelivery(deliveryId);
	}
}
