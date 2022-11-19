package com.fonsecaworks.fonsecalogistics.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryRecipientDTO {
	
	private String name;
	private String address;
	private String streetNumber;
	private String addressComplement;
	private String district;

}
