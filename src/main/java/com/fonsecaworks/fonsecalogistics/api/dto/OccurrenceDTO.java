package com.fonsecaworks.fonsecalogistics.api.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OccurrenceDTO {

	private Long id;
	private String description;
	private OffsetDateTime registerDate;
	
}
