package com.fonsecaworks.fonsecalogistics.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fonsecaworks.fonsecalogistics.domain.model.Customer;
import com.fonsecaworks.fonsecalogistics.domain.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerController {
	
	private CustomerRepository customerRepository;
	
	@GetMapping("/customers")
	public List<Customer> findAllCustomers() {
		return customerRepository.findAll();		
	}
	
}
