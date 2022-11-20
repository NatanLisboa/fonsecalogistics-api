package com.fonsecaworks.fonsecalogistics.domain.service;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.fonsecaworks.fonsecalogistics.domain.exception.EntityNotFoundException;
import com.fonsecaworks.fonsecalogistics.domain.model.Delivery;
import com.fonsecaworks.fonsecalogistics.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeliverySearchService {

	private final DeliveryRepository deliveryRepository;
	
	@Transactional
	public Delivery searchDelivery(Long deliveryId) {
		Delivery delivery = deliveryRepository.findById(deliveryId)
				.orElseThrow(() -> new EntityNotFoundException("Delivery not found"));
		Hibernate.initialize(delivery.getOccurrences());
		return delivery;
	}
	
}
