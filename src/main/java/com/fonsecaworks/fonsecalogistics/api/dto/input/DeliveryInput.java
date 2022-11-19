package com.fonsecaworks.fonsecalogistics.api.dto.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryInput {

	@Valid
	@NotNull
	private DeliveryCustomerInput customer;
	
	@Valid
	@NotNull
	private DeliveryRecipientInput recipient;
	
	@NotNull
	private String items;
	
	@NotNull
	private BigDecimal tax;
	
}
