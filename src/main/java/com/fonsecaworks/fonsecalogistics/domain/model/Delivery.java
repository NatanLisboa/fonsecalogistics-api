package com.fonsecaworks.fonsecalogistics.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.fonsecaworks.fonsecalogistics.domain.ValidationGroups;

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
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.CustomerId.class)
	@NotNull
	@ManyToOne
	private Customer customer;
	
	@NotNull
	@Valid
	@Embedded
	private Recipient recipient;
	
	@NotNull
	private BigDecimal tax;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private DeliveryStatus status;

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime orderDate;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime completionDate;

}
