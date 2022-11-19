package com.fonsecaworks.fonsecalogistics.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Recipient {
	
	@Column(name = "recipient_name")
	private String name;
	
	@Column(name = "recipient_address")
	private String address;
	
	@Column(name = "recipient_street_number")
	private String streetNumber;
	
	@Column(name = "recipient_address_complement")
	private String addressComplement;
	
	@Column(name = "recipient_district")
	private String district;

}
