package com.fonsecaworks.fonsecalogistics.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fonsecaworks.fonsecalogistics.domain.exception.CustomerNotFoundException;
import com.fonsecaworks.fonsecalogistics.domain.exception.EmailAlreadyUsedException;
import com.fonsecaworks.fonsecalogistics.domain.model.Customer;
import com.fonsecaworks.fonsecalogistics.domain.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerCatalogService {
	
	private CustomerRepository customerRepository;
	
	public Customer findCustomerById(Long customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with this id"));
		
	}
	
	@Transactional
	public Customer save(Customer customer) {
		boolean emailAlreadyUsed = customerRepository.findByEmail(customer.getEmail())
				.stream()
				.anyMatch(alreadyExistentCustomer -> !alreadyExistentCustomer.equals(customer));
		
		if (emailAlreadyUsed) {
			throw new EmailAlreadyUsedException("There is already a customer registered with this email");
		}
		
		return customerRepository.save(customer);
	}
	
	@Transactional
	public void delete(Long customerId) {
		customerRepository.deleteById(customerId);
	}
	
}
