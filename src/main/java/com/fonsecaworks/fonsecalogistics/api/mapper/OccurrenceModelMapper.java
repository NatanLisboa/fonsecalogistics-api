package com.fonsecaworks.fonsecalogistics.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fonsecaworks.fonsecalogistics.api.dto.OccurrenceDTO;
import com.fonsecaworks.fonsecalogistics.domain.model.Occurrence;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OccurrenceModelMapper {

	private final ModelMapper modelMapper;
	
	public OccurrenceDTO toDTO(Occurrence occurrence) {
		return modelMapper.map(occurrence, OccurrenceDTO.class);
	}
	
	public List<OccurrenceDTO> toDTOList(List<Occurrence> occurrenceList) {
		return occurrenceList.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	
}
