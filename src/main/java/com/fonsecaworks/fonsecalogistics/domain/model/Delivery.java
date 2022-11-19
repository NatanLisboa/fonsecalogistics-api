package com.fonsecaworks.fonsecalogistics.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Customer customer;
	
	@Embedded
	private Recipient recipient;
	
	private String items;
	
	@NotNull
	private BigDecimal tax;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	private OffsetDateTime orderDate;
	
	private OffsetDateTime completionDate;

}
