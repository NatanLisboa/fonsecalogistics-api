package com.fonsecaworks.fonsecalogistics.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fonsecaworks.fonsecalogistics.domain.model.Customer;

@RestController
public class CustomerController {

	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		
		Customer customer1 = new Customer();
		customer1.setId(1L);
		customer1.setName("Natan");
		customer1.setEmail("natan@email.com");
		customer1.setPhone("5511987654321");
		
		var customer2 = new Customer();
		customer2.setId(2L);
		customer2.setName("Gabriel");
		customer2.setEmail("gabriel@email.com");
		customer2.setPhone("5511923456789");

		return Arrays.asList(customer1, customer2);
		
	}
	
}
