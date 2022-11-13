package com.fonsecaworks.fonsecalogistics.domain.service;

import java.time.LocalDateTime;

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

	private CustomerCatalogService customerCatalogService;
	private DeliveryRepository deliveryRepository;
	
	@Transactional
	public Delivery createDeliveryRequest(Delivery delivery) {
		
		Customer customer = customerCatalogService.findCustomerById(delivery.getCustomer().getId());
				
		delivery.setCustomer(customer);
		delivery.setStatus(DeliveryStatus.PENDING);
		delivery.setOrderDate(LocalDateTime.now());
		
		return deliveryRepository.save(delivery);
		
	}
}
