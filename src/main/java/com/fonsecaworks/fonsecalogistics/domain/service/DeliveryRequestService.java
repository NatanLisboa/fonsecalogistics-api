package com.fonsecaworks.fonsecalogistics.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fonsecaworks.fonsecalogistics.domain.model.Customer;
import com.fonsecaworks.fonsecalogistics.domain.model.Delivery;
import com.fonsecaworks.fonsecalogistics.domain.model.DeliveryStatus;
import com.fonsecaworks.fonsecalogistics.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeliveryRequestService {

	private final CustomerCatalogService customerCatalogService;
	private final DeliveryRepository deliveryRepository;
	
	@Transactional
	public Delivery createDeliveryRequest(Delivery delivery) {
		
		Customer customer = customerCatalogService.findCustomerById(delivery.getCustomer().getId());
				
		delivery.setCustomer(customer);
		delivery.setStatus(DeliveryStatus.PENDING);
		delivery.setOrderDate(OffsetDateTime.now());
		
		return deliveryRepository.save(delivery);
		
	}
}
