package com.fonsecaworks.fonsecalogistics.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fonsecaworks.fonsecalogistics.domain.model.Delivery;
import com.fonsecaworks.fonsecalogistics.domain.model.Occurrence;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OccurrenceRegistrationService {

	private final DeliverySearchService deliverySearchService;
	
	@Transactional
	public Occurrence registerOccurrence(Long deliveryId, String description) {
		Delivery delivery = deliverySearchService.searchDelivery(deliveryId);
		return delivery.addOccurrence(description);
	}
	
	
}
