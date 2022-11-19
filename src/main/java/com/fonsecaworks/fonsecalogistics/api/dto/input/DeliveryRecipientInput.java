package com.fonsecaworks.fonsecalogistics.api.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryRecipientInput {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private String streetNumber;
	
	private String addressComplement;
	
	@NotBlank
	private String district;

}
