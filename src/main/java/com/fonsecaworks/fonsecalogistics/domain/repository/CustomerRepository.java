package com.fonsecaworks.fonsecalogistics.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fonsecaworks.fonsecalogistics.domain.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}
