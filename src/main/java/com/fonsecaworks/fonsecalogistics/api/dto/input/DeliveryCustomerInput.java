package com.fonsecaworks.fonsecalogistics.api.dto.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryCustomerInput {

	@NotNull
	private Long id;
	
}
