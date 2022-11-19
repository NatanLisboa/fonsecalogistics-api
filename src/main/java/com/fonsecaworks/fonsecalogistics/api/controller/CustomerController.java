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
import com.fonsecaworks.fonsecalogistics.api.dto.CustomerDTO;
import com.fonsecaworks.fonsecalogistics.api.dto.input.CustomerInput;
import com.fonsecaworks.fonsecalogistics.api.mapper.CustomerModelMapper;
import com.fonsecaworks.fonsecalogistics.domain.model.Customer;
import com.fonsecaworks.fonsecalogistics.domain.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
	
	private final CustomerRepository customerRepository;
	private final CustomerCatalogService customerCatalogService;
	private final CustomerModelMapper customerModelMapper;
	
	@GetMapping
	public List<CustomerDTO> findAllCustomers() {
		return customerModelMapper.toDTOList(customerRepository.findAll());		
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDTO> findCustomerById(@PathVariable Long customerId) {
		return customerRepository.findById(customerId)
				.map(customerModelMapper::toDTO)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO saveNewCustomer(@Valid @RequestBody CustomerInput customerInput) {
		Customer customer = customerModelMapper.toEntity(customerInput);
		return customerModelMapper.toDTO(customerCatalogService.save(customer));
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customerId,
			@Valid @RequestBody CustomerInput customerNewData) {
		
		if (!customerRepository.existsById(customerId)) {
			return ResponseEntity.notFound().build();
		}
		
		Customer customer = customerModelMapper.toEntity(customerNewData);
		
		customer.setId(customerId);
		return ResponseEntity.ok(customerModelMapper.toDTO(customerCatalogService.save(customer)));
		
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
