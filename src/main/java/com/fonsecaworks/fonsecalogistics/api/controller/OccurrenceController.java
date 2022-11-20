package com.fonsecaworks.fonsecalogistics.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fonsecaworks.fonsecalogistics.api.dto.OccurrenceDTO;
import com.fonsecaworks.fonsecalogistics.api.dto.input.OccurrenceInput;
import com.fonsecaworks.fonsecalogistics.api.mapper.OccurrenceModelMapper;
import com.fonsecaworks.fonsecalogistics.domain.model.Delivery;
import com.fonsecaworks.fonsecalogistics.domain.model.Occurrence;
import com.fonsecaworks.fonsecalogistics.domain.service.DeliverySearchService;
import com.fonsecaworks.fonsecalogistics.domain.service.OccurrenceRegistrationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/deliveries/{deliveryId}/occurrences")
@AllArgsConstructor
public class OccurrenceController {
	
	private final DeliverySearchService deliverySearchService;
	private final OccurrenceRegistrationService occurrenceRegistrationService; 
	private final OccurrenceModelMapper occurrenceModelMapper;
	
	@GetMapping
	public List<OccurrenceDTO> findAllOccurrences(@PathVariable Long deliveryId) {
		Delivery delivery = deliverySearchService.searchDelivery(deliveryId);
		return occurrenceModelMapper.toDTOList(delivery.getOccurrences());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OccurrenceDTO registerOccurrence(@PathVariable Long deliveryId,
			@Valid @RequestBody OccurrenceInput occurrenceInput) {
		Occurrence registeredOccurrence = occurrenceRegistrationService.registerOccurrence(deliveryId, occurrenceInput.getDescription());
		return occurrenceModelMapper.toDTO(registeredOccurrence);
	}
	
}
