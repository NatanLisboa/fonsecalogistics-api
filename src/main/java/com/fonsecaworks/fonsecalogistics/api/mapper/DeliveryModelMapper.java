package com.fonsecaworks.fonsecalogistics.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fonsecaworks.fonsecalogistics.api.dto.DeliveryDTO;
import com.fonsecaworks.fonsecalogistics.api.dto.input.DeliveryInput;
import com.fonsecaworks.fonsecalogistics.domain.model.Delivery;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DeliveryModelMapper {

	private ModelMapper modelMapper;
	
	public DeliveryDTO toDTO(Delivery delivery) {
		return modelMapper.map(delivery, DeliveryDTO.class);
	}
	
	public List<DeliveryDTO> toDTOList(List<Delivery> deliveryList) {
		return deliveryList.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	public Delivery toEntity(DeliveryInput deliveryInput) {
		return modelMapper.map(deliveryInput, Delivery.class);
	}
	
}
