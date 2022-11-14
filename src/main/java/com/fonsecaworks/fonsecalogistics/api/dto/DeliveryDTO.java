package com.fonsecaworks.fonsecalogistics.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fonsecaworks.fonsecalogistics.domain.model.DeliveryStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryDTO {
	
	private Long id;
	private String customerName;
	private RecipientDTO recipient;
	private BigDecimal tax;
	private DeliveryStatus status;
	private OffsetDateTime orderDate;
	private OffsetDateTime completionDate;

}
