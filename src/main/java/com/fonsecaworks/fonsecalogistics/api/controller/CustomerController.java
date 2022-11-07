package com.fonsecaworks.fonsecalogistics.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fonsecaworks.fonsecalogistics.domain.model.Customer;

@RestController
public class CustomerController {

	@PersistenceContext
	private EntityManager em;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		return em.createQuery("from Customer", Customer.class)
				.getResultList();		
	}
	
}
