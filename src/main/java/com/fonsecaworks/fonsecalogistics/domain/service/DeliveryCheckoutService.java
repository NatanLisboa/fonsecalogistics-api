package com.fonsecaworks.fonsecalogistics.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fonsecaworks.fonsecalogistics.domain.model.Delivery;
import com.fonsecaworks.fonsecalogistics.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeliveryCheckoutService {

	private final DeliveryRepository deliveryRepository;
	private final DeliverySearchService deliverySearchService;
	
	@Transactional
	public void checkoutDelivery(Long deliveryId) {
		Delivery delivery = deliverySearchService.searchDelivery(deliveryId);
		
		delivery.checkout();
		
		deliveryRepository.save(delivery);
	}
	
}
