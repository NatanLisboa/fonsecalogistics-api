package com.fonsecaworks.fonsecalogistics.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fonsecaworks.fonsecalogistics.api.dto.CustomerDTO;
import com.fonsecaworks.fonsecalogistics.api.dto.input.CustomerInput;
import com.fonsecaworks.fonsecalogistics.domain.model.Customer;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomerModelMapper {
	
	private ModelMapper modelMapper;
	
	public CustomerDTO toDTO(Customer customer){
		return modelMapper.map(customer, CustomerDTO.class);
	}
	
	public List<CustomerDTO> toDTOList(List<Customer> customerList) {
		return customerList.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	public Customer toEntity(CustomerInput customerInput) {
		return modelMapper.map(customerInput, Customer.class);
	}

}
