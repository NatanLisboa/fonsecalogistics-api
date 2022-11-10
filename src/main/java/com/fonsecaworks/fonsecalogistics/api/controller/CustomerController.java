package com.fonsecaworks.fonsecalogistics.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fonsecaworks.fonsecalogistics.domain.service.CustomerCatalogService;
import com.fonsecaworks.fonsecalogistics.domain.model.Customer;
import com.fonsecaworks.fonsecalogistics.domain.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
	
	private CustomerRepository customerRepository;
	private CustomerCatalogService customerCatalogService;
	
	@GetMapping
	public List<Customer> findAllCustomers() {
		return customerRepository.findAll();		
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable Long customerId) {
		return customerRepository.findById(customerId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer saveNewCustomer(@Valid @RequestBody Customer customer) {
		return customerCatalogService.save(customer);
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId,
			@Valid @RequestBody Customer customerNewData) {
		
		if (!customerRepository.existsById(customerId)) {
			return ResponseEntity.notFound().build();
		}
		
		customerNewData.setId(customerId);
		return ResponseEntity.ok(customerCatalogService.save(customerNewData));
		
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
		
		if (!customerRepository.existsById(customerId)) {
			return ResponseEntity.notFound().build();
		}
		
		customerCatalogService.delete(customerId);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
